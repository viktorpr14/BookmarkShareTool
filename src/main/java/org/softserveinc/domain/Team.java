package org.softserveinc.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vv on 05.12.2014.
 */
@Entity
@Table(name = "TEAM")
public class Team {

    @Expose
    private Integer teamId;
    @Expose
    private String teamName;
    private Set<UserTeam> usersTeams = new HashSet<UserTeam>();

    public Team() {

    }

    public Team(String teamName) {
        this.teamName = teamName;
    }

    public Team(String teamName, Set<UserTeam> usersTeams) {
        this.teamName = teamName;
        this.usersTeams = usersTeams;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

//    @Transient
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "team")
    public Set<UserTeam> getUsersTeams() {
        return usersTeams;
    }

    public void setUsersTeams(Set<UserTeam> usersTeams) {
        this.usersTeams = usersTeams;
    }
}
