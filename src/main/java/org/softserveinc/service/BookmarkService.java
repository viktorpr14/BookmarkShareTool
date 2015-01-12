package org.softserveinc.service;

import org.softserveinc.domain.Bookmark;
import org.softserveinc.repository.HibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookmarkService {
    @Autowired
    HibernateDAO hibernateDAO;

    public void saveBookmark(Bookmark bookmark) {
        bookmark.setCreated(new java.sql.Date(new java.util.Date().getTime()));
        hibernateDAO.saveBookmarkIntoDB(bookmark);
    }
}


