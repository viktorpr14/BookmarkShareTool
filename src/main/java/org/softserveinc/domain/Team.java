package org.softserveinc.domain;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity for storing teams into db
 */
@Entity
@Table(name = "TEAM")
public class Team {

    @Expose
    private Integer teamId;
    @Expose
    private String teamName;

    public Team() {

    }

    public Team(String teamName) {
        this.teamName = teamName;
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
}
