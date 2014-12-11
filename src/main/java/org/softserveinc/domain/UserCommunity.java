package org.softserveinc.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vv on 09.12.2014.
 */

@Entity
@Table(name="USERS_COMMUNITIES")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "pk.community", joinColumns = @JoinColumn(name = "COMMUNITY_ID"))
})
public class UserCommunity implements Serializable {

//    @EmbeddedId
    private UserCommunityId pk = new UserCommunityId();

//    @Column(name = "IS_MEMBER")
    private boolean isMember;

    public UserCommunity() {

    }

//    @EmbeddedId
//    public UserCommunityId getpKey() {
//        return pKey;
//    }
//
//    public void setpKey(UserCommunityId pKey) {
//        this.pKey = pKey;
//    }

    @EmbeddedId
    public UserCommunityId getPk() {
        return pk;
    }

    public void setPk(UserCommunityId pk) {
        this.pk = pk;
    }

    @Column(name = "IS_MEMBER")
    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean isMember) {
        this.isMember = isMember;
    }

    @Transient
    public User getUser() {
        return getPk().getUser();
    }

    public void setUser(User user) {
        getPk().setUser(user);
    }

    @Transient
    public Community getCommunity() {
        return getPk().getCommunity();
    }

    public void setCommunity(Community community) {
        getPk().setCommunity(community);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCommunity that = (UserCommunity) o;

        if (isMember != that.isMember) return false;
        if (getPk() != null ? !getPk().equals(that.getPk()) : that.getPk() != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pk != null ? pk.hashCode() : 0;
        result = 31 * result + (isMember ? 1 : 0);
        return result;
    }
}
