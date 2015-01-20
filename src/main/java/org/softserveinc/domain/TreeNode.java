package org.softserveinc.domain;

import java.util.List;

/**
 * Created by vv on 14.01.2015.
 */
public class TreeNode {
    private String folderName;
    private List<Bookmark> listOfBookmarks;
    private List<TreeNode> listOfTreeNodes;

    public TreeNode() {

    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public List<Bookmark> getListOfBookmarks() {
        return listOfBookmarks;
    }

    public void setListOfBookmarks(List<Bookmark> listOfBookmarks) {
        this.listOfBookmarks = listOfBookmarks;
    }

    public List<TreeNode> getListOfTreeNodes() {
        return listOfTreeNodes;
    }

    public void setListOfTreeNodes(List<TreeNode> listOfTreeNodes) {
        this.listOfTreeNodes = listOfTreeNodes;
    }
}
