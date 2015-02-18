package org.softserveinc.domain;

/**
 * Wrapper that represent user on UI
 */
public class UserView extends User{
    private Boolean linkedWithGoogle = false;
    private Boolean linkedWithFacebook = false;

    public UserView (User user) {
        this.userId = user.userId;
        this.firstName = user.firstName;
        this.lastName = user.lastName;
        this.username = user.username;
        this.email = user.email;
        this.password = user.password;
    }

    public Boolean getLinkedWithGoogle() {
        return linkedWithGoogle;
    }

    public void setLinkedWithGoogle(Boolean linkedWithGoogle) {
        this.linkedWithGoogle = linkedWithGoogle;
    }

    public Boolean getLinkedWithFacebook() {
        return linkedWithFacebook;
    }

    public void setLinkedWithFacebook(Boolean linkedWithFacebook) {
        this.linkedWithFacebook = linkedWithFacebook;
    }
}
