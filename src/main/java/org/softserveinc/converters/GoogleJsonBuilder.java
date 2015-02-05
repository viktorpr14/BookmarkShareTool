package org.softserveinc.converters;

import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.repository.HibernateDAO;
import org.softserveinc.service.BookmarkService;
import org.softserveinc.util.GoogleTreeNode;
import org.softserveinc.util.GoogleTreeNodeWrapper;
import org.softserveinc.util.NodeType;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.*;

@Service
public class GoogleJsonBuilder{

    @Inject
    BookmarkService bookmarkService;
    @Inject
    HibernateDAO hibernateDAO;

    Map<Integer, Bookmark> mapOfIdsAndBookmarks;


    public GoogleTreeNodeWrapper buildJson(String userId) {
        GoogleTreeNodeWrapper googleTreeNodeWrapper =new GoogleTreeNodeWrapper();
        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
        if(bookmarkReferences.size() == 0) {
            return googleTreeNodeWrapper;
        }
        Set<Integer> bookmarkIds= new HashSet<>();
        mapOfIdsAndBookmarks = new HashMap<>();

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }
        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        for (Bookmark bookmark : bookmarks) {
            mapOfIdsAndBookmarks.put(bookmark.getBookmarkId(), bookmark);
        }

        GoogleTreeNode synced = googleTreeNodeWrapper.getRoots().getSynced();
        synced.setChildren(new ArrayList<GoogleTreeNode>());

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            List<String> chainOfFolders = bookmarkReference.getPath()==null?null:new ArrayList<>(Arrays.asList(bookmarkReference.getPath().split("/")));
            buildTree(chainOfFolders, synced, bookmarkReference);
        }
        return googleTreeNodeWrapper;


    }


    private void buildTree( List<String> chainOfFolders, GoogleTreeNode parentNode, BookmarkReference bookmarkReference){
        // chainOfFolders == null when it's bookmark - most inner node
        if(chainOfFolders==null||chainOfFolders.size()==0){
            parentNode.getChildren().add(new GoogleTreeNode(null,
                    bookmarkReference.getCreated(),
                    bookmarkReference.getCreated()
                    ,bookmarkReference.getBookmarkReferenceId(),
                    mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId()).getName(),
                    NodeType.url,
                    mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId()).getURL()));
            return;
        }
        //Logic to find out is next node bookmark or folder
        for (GoogleTreeNode existingNode : parentNode.getChildren()) {

            if(chainOfFolders.get(0).equals(existingNode.getName())){
                if(chainOfFolders.size()>1){
                    chainOfFolders.remove(0);
                    buildTree(chainOfFolders, existingNode,bookmarkReference);
                    return;
                }
                    buildTree(null, existingNode,bookmarkReference);
                    return;

            }
        }

        //Adding folder
        GoogleTreeNode newFolderNode = new GoogleTreeNode(new ArrayList<GoogleTreeNode>(),
                bookmarkReference.getCreated(),
                bookmarkReference.getCreated()
                ,bookmarkReference.getBookmarkReferenceId(),
                chainOfFolders.get(0),
                NodeType.folder,
                null);
        parentNode.getChildren().add(newFolderNode);
        chainOfFolders.remove(0);
        buildTree(chainOfFolders,newFolderNode,bookmarkReference);
    }
}
