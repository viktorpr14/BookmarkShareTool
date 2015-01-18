package org.softserveinc.service;

import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.domain.TreeNode;
import org.softserveinc.domain.User;
import org.softserveinc.repository.HibernateDAO;
import org.softserveinc.util.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class BookmarkService {
    @Autowired
    HibernateDAO hibernateDAO;

    public void saveBookmark(Bookmark bookmark) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        hibernateDAO.saveBookmarkIntoDB(bookmark,ReferenceType.USER);
    }

    public List<Bookmark> getBookmarksByTeamId(String teamId) {
        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByTeamId(teamId);
        Set<Integer> bookmarkIds= new HashSet<>();
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }
        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        return bookmarks;
    }

/*
    public List<Bookmark> getBookmarksByUserId(String userId) {
        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
        Set<Integer> bookmarkIds= new HashSet<>();
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }
        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        return bookmarks;
    }
*/

    public TreeNode getBookmarksByUserId(String userId) {
        Set<Integer> bookmarkIds= new HashSet<>();

        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setFolderName("USER");
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());
        mainTreeNode.setBookmarkIds(new ArrayList<String>());

        Map<Integer, Bookmark> idsAndBookmarks = new HashMap<Integer, Bookmark>();
        List<String> pathAndIds = new ArrayList<String>();

        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
        if(bookmarkReferences.size() == 0) {
            return mainTreeNode;
        }

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }

        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        for (Bookmark bookmark : bookmarks) {
            idsAndBookmarks.put(bookmark.getBookmarkId(), bookmark);
        }

        //get tree of the folders
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            String path = bookmarkReference.getPath();

            if(path == null) {
                mainTreeNode.getBookmarkIds().add(bookmarkReference.getBookmarkId().toString());
            } else {
                String pathAndId = path + ":" + bookmarkReference.getBookmarkId();
                pathAndIds.add(pathAndId);
            }
        }

        Collections.sort(pathAndIds);

        for (String pathAndId : pathAndIds) {
            String path = pathAndId.substring(0, pathAndId.indexOf(":"));
            List<String> foldersInner = Arrays.asList(path.split("/"));
            Integer bookmarkId = Integer.parseInt(pathAndId.substring(pathAndId.indexOf(":") + 1));

            buildTree(foldersInner, mainTreeNode, bookmarkId);
        }

        return mainTreeNode;
    }

    private Boolean buildTree(List<String> folders, TreeNode parentTreeNode, Integer bookmarkId) {
        boolean flag = true;
        List<String> foldersInner = new ArrayList<String>(folders);
        String folder = folders.get(0);
        List<TreeNode> parentTreeNodes = parentTreeNode.getListOfTreeNodes();

        for (TreeNode treeNode : parentTreeNodes) {

            if(treeNode.getFolderName().equals(folder)) {
                foldersInner.remove(0);

                if(foldersInner.size() > 0) {
                    flag = buildTree(foldersInner, treeNode, bookmarkId);
                } else {
                    TreeNode nestedTreeNode = new TreeNode();
                    nestedTreeNode.setBookmarkIds(new ArrayList<String>());
                    nestedTreeNode.getBookmarkIds().add(bookmarkId.toString());

                    treeNode.getListOfTreeNodes().add(nestedTreeNode);

                    return false;
                }
            }
        }

        if(flag) {
            createParallelBranch(foldersInner, parentTreeNode, bookmarkId);
        }

        flag = false;
        return flag;
    }

    private void createParallelBranch(List<String> folders, TreeNode parentTreeNode, Integer bookmarkId) {
        List<String> foldersInner = new ArrayList<String>(folders);
        String folder = folders.get(0);

        TreeNode treeNode = new TreeNode();
        treeNode.setFolderName(folder);
        treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());
        treeNode.setBookmarkIds(new ArrayList<String>());

        parentTreeNode.getListOfTreeNodes().add(treeNode);

        foldersInner.remove(0);
        if(foldersInner.size() > 0) {
            createParallelBranch(foldersInner, treeNode, bookmarkId);
        } else  {
            TreeNode nestedTreeNode = new TreeNode();
            nestedTreeNode.setBookmarkIds(new ArrayList<String>());
            nestedTreeNode.getBookmarkIds().add(bookmarkId.toString());

            treeNode.getListOfTreeNodes().add(nestedTreeNode);
        }
    }

}


