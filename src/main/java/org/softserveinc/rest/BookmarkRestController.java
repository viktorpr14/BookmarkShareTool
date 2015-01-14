package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.domain.Bookmark;
import org.softserveinc.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class BookmarkRestController {
    @Inject
    private BookmarkService bookmarkService;

    @RequestMapping(value = "/rest/bookmark", method = RequestMethod.POST)
    public void createNewBookmark(@RequestBody Bookmark bookmark) {
        bookmarkService.saveBookmark(bookmark);

    }

    @RequestMapping(value = "/rest/team/bookmarks/{teamId}", method = RequestMethod.GET)
    public String getBookmarksByTeamId(@PathVariable("teamId") String teamId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByTeamId(teamId);
        return new Gson().toJson(bookmarks);
    }

    @RequestMapping(value = "/rest/user/bookmarks/{userId}", method = RequestMethod.GET)
    public String getBookmarksByUserId(@PathVariable("userId") String userId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByUserId(userId);
        return new Gson().toJson(bookmarks);
    }

}
