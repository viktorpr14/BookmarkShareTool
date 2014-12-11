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

    private UserTeamId pk = new UserTeamId();
    private boolean isMember;

    public UserTeam() {

    }

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
}
