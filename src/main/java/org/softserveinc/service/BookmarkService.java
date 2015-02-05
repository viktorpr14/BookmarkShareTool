package org.softserveinc.service;

import org.softserveinc.DTO.BookmarkDTO;
import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.util.TreeNode;
import org.softserveinc.domain.User;
import org.softserveinc.repository.HibernateDAO;
import org.softserveinc.util.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.*;


@Service
public class BookmarkService {
    @Autowired
    HibernateDAO hibernateDAO;

    public void saveBookmark(BookmarkDTO bookmarkDTO) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Bookmark bookmark= new Bookmark(bookmarkDTO.getName(),bookmarkDTO.getURL(),bookmarkDTO.getDescription());
        Integer bookmarkID= hibernateDAO.saveBookmarkIntoDB(bookmark);
        //TODO make possibility to add bookmark with reference to team
        BookmarkReference bookmarkReference = new BookmarkReference(bookmarkID, bookmarkDTO.getPath(), new Date(System.currentTimeMillis()),user.getUserId(),ReferenceType.USER);
        hibernateDAO.saveBookmarkReference(bookmarkReference);
    }

/*
    public List<Bookmark> getBookmarksByTeamId(String teamId) {
        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByTeamId(teamId);
        Set<Integer> bookmarkIds= new HashSet<>();
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }
        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        return bookmarks;
    }
*/

    public TreeNode getTreeOfBookmarksByTeamId(String teamId) {
        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setFolderName("TEAM");
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByTeamId(teamId);
        if(bookmarkReferences.size() > 0) {
            mainTreeNode = buildTreeOfBookmarks(bookmarkReferences);
        }

        return mainTreeNode;
}


    public TreeNode getTreeOfBookmarksByUserId(String userId) {
        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setFolderName("USER");
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
        if(bookmarkReferences.size() > 0) {
            mainTreeNode = buildTreeOfBookmarks(bookmarkReferences);
        }

        return mainTreeNode;
    }

    private TreeNode buildTreeOfBookmarks(List<BookmarkReference> bookmarkReferences) {
        Set<Integer> bookmarkIds= new HashSet<>();

        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setFolderName("USER");
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        Map<Integer, Bookmark> mapOfIdsAndBookmarks = new HashMap<Integer, Bookmark>();
        List<String> pathAndIds = new ArrayList<String>();

        //return empty tree if there are no bookmarks fo user
//        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
//        if(bookmarkReferences.size() == 0) {
//            return mainTreeNode;
//        }

        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }

        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        for (Bookmark bookmark : bookmarks) {
            mapOfIdsAndBookmarks.put(bookmark.getBookmarkId(), bookmark);
        }

        //get tree of the folders
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            String path = bookmarkReference.getPath();

            if(path == null) {
                Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId());

                TreeNode treeNode = new TreeNode();
                treeNode.setBookmark(bookmark);
                treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

                mainTreeNode.getListOfTreeNodes().add(treeNode);
            } else {
                String pathAndId = path + ":" + bookmarkReference.getBookmarkId();
                pathAndIds.add(pathAndId);
            }
        }

        if (pathAndIds.isEmpty()) {
            return mainTreeNode;
        }

        Collections.sort(pathAndIds);

        for (String pathAndId : pathAndIds) {
            String path = pathAndId.substring(0, pathAndId.indexOf(":"));
            List<String> chainOfFolders = Arrays.asList(path.split("/"));
            Integer bookmarkId = Integer.parseInt(pathAndId.substring(pathAndId.indexOf(":") + 1));

            buildTree(chainOfFolders, mainTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        return mainTreeNode;
    }

    private Boolean buildTree(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        boolean flag = true;
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);
        List<TreeNode> parentTreeNodes = parentTreeNode.getListOfTreeNodes();

        List<TreeNode> parentTreeNodesFoldersOnly = new ArrayList<TreeNode>();
        for (TreeNode treeNode : parentTreeNodes) {
            if(treeNode.getFolderName() != null) {
                parentTreeNodesFoldersOnly.add(treeNode);
            }
        }

        for (TreeNode treeNode : parentTreeNodesFoldersOnly) {

            if(treeNode.getFolderName().equals(folder)) {
                innerChainOfFolders.remove(0);

                if(innerChainOfFolders.size() > 0) {
                    flag = buildTree(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
                } else {
                    Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);

                    TreeNode nestedTreeNode = new TreeNode();
                    nestedTreeNode.setBookmark(bookmark);
                    nestedTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

                    treeNode.getListOfTreeNodes().add(nestedTreeNode);

                    return false;
                }
            }
        }

        if(flag) {
            createParallelBranch(innerChainOfFolders, parentTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        flag = false;
        return flag;
    }

    private void createParallelBranch(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);

        TreeNode treeNode = new TreeNode();
        treeNode.setFolderName(folder);
        treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

        parentTreeNode.getListOfTreeNodes().add(0, treeNode);

        innerChainOfFolders.remove(0);
        if(innerChainOfFolders.size() > 0) {
            createParallelBranch(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
        } else  {
            Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);

            TreeNode nestedTreeNode = new TreeNode();
            nestedTreeNode.setBookmark(bookmark);
            nestedTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());

            treeNode.getListOfTreeNodes().add(nestedTreeNode);
        }
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

/*
    public TreeNode getBookmarksByUserId(String userId) {
        Set<Integer> bookmarkIds= new HashSet<>();

        TreeNode mainTreeNode = new TreeNode();
        mainTreeNode.setFolderName("USER");
        mainTreeNode.setListOfTreeNodes(new ArrayList<TreeNode>());
        mainTreeNode.setListOfBookmarks(new ArrayList<Bookmark>());

        Map<Integer, Bookmark> mapOfIdsAndBookmarks = new HashMap<Integer, Bookmark>();
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
            mapOfIdsAndBookmarks.put(bookmark.getBookmarkId(), bookmark);
        }

        //get tree of the folders
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            String path = bookmarkReference.getPath();

            if(path == null) {
                Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkReference.getBookmarkId());
                mainTreeNode.getListOfBookmarks().add(bookmark);
            } else {
                String pathAndId = path + ":" + bookmarkReference.getBookmarkId();
                pathAndIds.add(pathAndId);
            }
        }

        Collections.sort(pathAndIds);

        for (String pathAndId : pathAndIds) {
            String path = pathAndId.substring(0, pathAndId.indexOf(":"));
            List<String> chainOfFolders = Arrays.asList(path.split("/"));
            Integer bookmarkId = Integer.parseInt(pathAndId.substring(pathAndId.indexOf(":") + 1));

            buildTree(chainOfFolders, mainTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        return mainTreeNode;
    }

    private Boolean buildTree(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        boolean flag = true;
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);
        List<TreeNode> parentTreeNodes = parentTreeNode.getListOfTreeNodes();

        for (TreeNode treeNode : parentTreeNodes) {

            if(treeNode.getFolderName().equals(folder)) {
                innerChainOfFolders.remove(0);

                if(innerChainOfFolders.size() > 0) {
                    flag = buildTree(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
                } else {
//                    TreeNode nestedTreeNode = new TreeNode();
//                    nestedTreeNode.setListOfBookmarkIds(new ArrayList<String>());
//                    nestedTreeNode.getListOfBookmarkIds().add(bookmarkId.toString());
//
//                    treeNode.getListOfTreeNodes().add(nestedTreeNode);
                    //treeNode.getListOfBookmarkIds().add(bookmarkId.toString());

                    Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);
                    treeNode.getListOfBookmarks().add(bookmark);

                    return false;
                }
            }
        }

        if(flag) {
            createParallelBranch(innerChainOfFolders, parentTreeNode, bookmarkId, mapOfIdsAndBookmarks);
        }

        flag = false;
        return flag;
    }

    private void createParallelBranch(List<String> chainOfFolders, TreeNode parentTreeNode, Integer bookmarkId, Map<Integer, Bookmark> mapOfIdsAndBookmarks) {
        List<String> innerChainOfFolders = new ArrayList<String>(chainOfFolders);
        String folder = chainOfFolders.get(0);

        TreeNode treeNode = new TreeNode();
        treeNode.setFolderName(folder);
        treeNode.setListOfTreeNodes(new ArrayList<TreeNode>());
        treeNode.setListOfBookmarks(new ArrayList<Bookmark>());

        parentTreeNode.getListOfTreeNodes().add(treeNode);

        innerChainOfFolders.remove(0);
        if(innerChainOfFolders.size() > 0) {
            createParallelBranch(innerChainOfFolders, treeNode, bookmarkId, mapOfIdsAndBookmarks);
        } else  {
//            TreeNode nestedTreeNode = new TreeNode();
//            nestedTreeNode.setListOfBookmarkIds(new ArrayList<String>());
//            nestedTreeNode.getListOfBookmarkIds().add(bookmarkId.toString());
//
//            treeNode.getListOfTreeNodes().add(nestedTreeNode);
            //treeNode.getListOfBookmarkIds().add(bookmarkId.toString());

            Bookmark bookmark = mapOfIdsAndBookmarks.get(bookmarkId);
            treeNode.getListOfBookmarks().add(bookmark);

        }
    }
*/

}


