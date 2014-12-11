package org.softserveinc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vv on 05.12.2014.
 */
@Entity
@Table(name = "TEAM")
public class Team implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "COMMUNITY_ID")
    private Integer teamId;

    private String teamName;

//    @ManyToMany(mappedBy = "communities")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Set<User> users = new HashSet<User>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.community")
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
    @Column(name = "TEAM_ID")
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.team")
    public Set<UserTeam> getUsersTeams() {
        return usersTeams;
    }

    public void setUsersTeams(Set<UserTeam> usersTeams) {
        this.usersTeams = usersTeams;
    }

    //    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
