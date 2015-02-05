package org.softserveinc.domain;

import org.softserveinc.util.ReferenceType;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Component
@Entity
@Table(name = "BOOKMARK_REFERENCE")
public class BookmarkReference {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bookmarkReferenceId;
    @NotNull
    private Integer bookmarkId;
    private String path;
    private Date created;
    @NotNull
    private Integer referenceId;
    @Enumerated(EnumType.STRING)
    private ReferenceType referenceType;

    public BookmarkReference() {
    }

    public BookmarkReference(Integer bookmarkId, String path, Date created, Integer referenceId, ReferenceType referenceType) {
        this.bookmarkId = bookmarkId;
        this.path = path;
        this.created = created;
        this.referenceId = referenceId;
        this.referenceType = referenceType;
    }

    public Integer getBookmarkReferenceId() {
        return bookmarkReferenceId;
    }

    public void setBookmarkReferenceId(Integer bookmarkReferenceId) {
        this.bookmarkReferenceId = bookmarkReferenceId;
    }

    public Integer getBookmarkId() {
        return bookmarkId;
    }

    public void setBookmarkId(Integer bookmarkId) {
        this.bookmarkId = bookmarkId;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public ReferenceType getReferenceType() {
        return referenceType;
    }

    public void setReferenceType(ReferenceType referenceType) {
        this.referenceType = referenceType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}


