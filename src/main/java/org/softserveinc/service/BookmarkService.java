package org.softserveinc.service;

import org.softserveinc.domain.Bookmark;
import org.softserveinc.domain.BookmarkReference;
import org.softserveinc.domain.User;
import org.softserveinc.repository.HibernateDAO;
import org.softserveinc.util.ReferenceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    public List<Bookmark> getBookmarksByUserId(String userId) {
        List<BookmarkReference> bookmarkReferences = hibernateDAO.getReferenceByUserId(userId);
        Set<Integer> bookmarkIds= new HashSet<>();
        for (BookmarkReference bookmarkReference : bookmarkReferences) {
            bookmarkIds.add(bookmarkReference.getBookmarkId());
        }
        List<Bookmark> bookmarks = hibernateDAO.getBookmarksByIds(bookmarkIds);
        return bookmarks;
    }
}


