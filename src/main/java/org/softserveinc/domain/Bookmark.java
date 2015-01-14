package org.softserveinc.domain;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
@Entity
@Table(name = "BOOKMARK")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookmarkId;
    private String name;
    @NotNull
    private String URL;
    private String description;

    public Bookmark() {
    }

    public Bookmark(String name, String URL, String path, String description, Date created) {
        this.name = name;
        this.URL = URL;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
