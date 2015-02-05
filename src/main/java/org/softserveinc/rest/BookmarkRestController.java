package org.softserveinc.rest;

import com.google.gson.Gson;
import org.softserveinc.DTO.BookmarkDTO;
import org.softserveinc.util.TreeNode;
import org.softserveinc.service.BookmarkService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


@RestController
public class BookmarkRestController {
    @Inject
    private BookmarkService bookmarkService;

    @RequestMapping(value = "/rest/bookmark", method = RequestMethod.POST)
    public void createNewBookmark(@RequestBody BookmarkDTO bookmarkDTO) {
        bookmarkService.saveBookmark(bookmarkDTO);
    }

/*
    @RequestMapping(value = "/rest/team/bookmarks/{teamId}", method = RequestMethod.GET)
    public String getBookmarksByTeamId(@PathVariable("teamId") String teamId) {
        List<Bookmark> bookmarks = bookmarkService.getBookmarksByTeamId(teamId);
        return new Gson().toJson(bookmarks);
    }
*/

    @RequestMapping(value = "/rest/team/bookmarks/{teamId}", method = RequestMethod.GET)
    public String getTreeOfBookmarksByTeamId(@PathVariable("teamId") String teamId) {
        TreeNode treeNode = bookmarkService.getTreeOfBookmarksByTeamId(teamId);
        return new Gson().toJson(treeNode);
    }

    @RequestMapping(value = "/rest/user/bookmarks/{userId}", method = RequestMethod.GET)
    public String getTreeOfBookmarksByUserId(@PathVariable("userId") String userId) {
        TreeNode treeNode = bookmarkService.getTreeOfBookmarksByUserId(userId);
        return new Gson().toJson(treeNode);
    }

}
