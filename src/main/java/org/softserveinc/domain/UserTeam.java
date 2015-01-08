package org.softserveinc.domain;

import javax.persistence.*;

/**
 * Created by vv on 09.12.2014.
 */

@Entity
@Table(name="USERS_TEAMS")
public class UserTeam {

    private Integer userTeamId;
//    private boolean isMember;
    private Team team;
    private Integer userId;
    private String status;

    public UserTeam() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getUserTeamId() {
        return userTeamId;
    }

    public void setUserTeamId(Integer userTeamId) {
        this.userTeamId = userTeamId;
    }

    @ManyToOne
    @JoinColumn(name="teamId")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

//    public boolean isMember() {
//        return isMember;
//    }

//    public void setMember(boolean isMember) {
//        this.isMember = isMember;
//    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
