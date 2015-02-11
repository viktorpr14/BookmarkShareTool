package org.softserveinc.util;

import org.softserveinc.domain.Bookmark;

import java.util.List;

public class TreeNode {
    private String folderName;
    private Bookmark bookmark;
    private List<TreeNode> listOfTreeNodes;

    public TreeNode() {

    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public Bookmark getBookmark() {
        return bookmark;
    }

    public void setBookmark(Bookmark bookmark) {
        this.bookmark = bookmark;
    }

    public List<TreeNode> getListOfTreeNodes() {
        return listOfTreeNodes;
    }

    public void setListOfTreeNodes(List<TreeNode> listOfTreeNodes) {
        this.listOfTreeNodes = listOfTreeNodes;
    }
}
