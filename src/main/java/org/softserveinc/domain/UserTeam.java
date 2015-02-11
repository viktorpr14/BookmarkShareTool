package org.softserveinc.domain;

import javax.persistence.*;

/**
 * Entity for storing relationship between users and teams into db
 */

@Entity
@Table(name="USERS_TEAMS")
public class UserTeam {

    private Integer userTeamId;
    private Integer userId;
    private Integer teamId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
