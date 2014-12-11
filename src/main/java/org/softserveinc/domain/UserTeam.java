package org.softserveinc.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vv on 09.12.2014.
 */

@Entity
@Table(name="USERS_TEAMS")
@AssociationOverrides({
        @AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "USER_ID")),
        @AssociationOverride(name = "pk.team", joinColumns = @JoinColumn(name = "TEAM_ID"))
})
public class UserTeam implements Serializable {

//    @EmbeddedId
    private UserTeamId pk = new UserTeamId();

//    @Column(name = "IS_MEMBER")
    private boolean isMember;

    public UserTeam() {

    }

//    @EmbeddedId
//    public UserTeamId getpKey() {
//        return pKey;
//    }
//
//    public void setpKey(UserTeamId pKey) {
//        this.pKey = pKey;
//    }

    @EmbeddedId
    public UserTeamId getPk() {
        return pk;
    }

    public void setPk(UserTeamId pk) {
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
    public Team getTeam() {
        return getPk().getTeam();
    }

    public void setTeam(Team team) {
        getPk().setTeam(team);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserTeam that = (UserTeam) o;

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
