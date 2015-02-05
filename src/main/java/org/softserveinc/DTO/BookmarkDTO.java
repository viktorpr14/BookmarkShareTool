package org.softserveinc.DTO;

import org.springframework.stereotype.Component;

@Component
public class BookmarkDTO {

    private String name;
    private String URL;
    private String path;
    private String description;

    public BookmarkDTO(String name, String URL, String path, String description) {
        this.name = name;
        this.URL = URL;
        this.path = path;
        this.description = description;
    }
    public BookmarkDTO() {
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


}
