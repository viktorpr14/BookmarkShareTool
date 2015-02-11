package org.softserveinc.util;

import org.softserveinc.domain.Bookmark;

import java.sql.Date;
import java.util.List;

public class GoogleTreeNode{
    private List<GoogleTreeNode> children;
    private Date date_added;
    private Date date_modified;
    private Integer id;
    private String name;
    private NodeType type;
    private String url;

    public GoogleTreeNode(List<GoogleTreeNode> children, Date date_added, Date date_modified, Integer id, String name, NodeType type, String url) {
        this.children = children;
        this.date_added = date_added;
        this.date_modified = date_modified;
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
    }
    public GoogleTreeNode(){}

    public List<GoogleTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<GoogleTreeNode> children) {
        this.children = children;
    }

    public Date getDate_added() {
        return date_added;
    }

    public void setDate_added(Date date_added) {
        this.date_added = date_added;
    }

    public Date getDate_modified() {
        return date_modified;
    }

    public void setDate_modified(Date date_modified) {
        this.date_modified = date_modified;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
