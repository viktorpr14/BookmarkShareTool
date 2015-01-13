package org.softserveinc.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
@Entity
@Table(name = "BOOKMARK")
public class Bookmark {
    private Integer bookmarkId;
    private String name;
    @NotNull
    private String URL;
    private String path;
    private String description;
    private Date created;
    private Integer userId;
    private Integer teamId;

    public Bookmark() {
    }

    public Bookmark(String name, String URL, String path, String description, Date created) {
        this.name = name;
        this.URL = URL;
        this.path = path;
        this.description = description;
        this.created = created;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
