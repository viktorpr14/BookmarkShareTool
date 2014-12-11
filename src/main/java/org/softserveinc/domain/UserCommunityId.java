package org.softserveinc.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by vv on 09.12.2014.
 */
@Embeddable
public class UserCommunityId implements Serializable{

//    @ManyToOne
    private User user;

//    @ManyToOne
    private Community community;

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCommunityId that = (UserCommunityId) o;

        if (community != null ? !community.equals(that.community) : that.community != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (community != null ? community.hashCode() : 0);
        return result;
    }
}
