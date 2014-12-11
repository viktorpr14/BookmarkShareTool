package org.softserveinc.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by vv on 05.12.2014.
 */
@Entity
@Table(name = "COMMUNITY")
public class Community implements Serializable{

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "COMMUNITY_ID")
    private Integer communityId;

    private String communityName;

//    @ManyToMany(mappedBy = "communities")
//    @NotFound(action = NotFoundAction.IGNORE)
//    private Set<User> users = new HashSet<User>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.community")
    private Set<UserCommunity> usersCommunities = new HashSet<UserCommunity>();

    public Community() {

    }

    public Community(String communityName) {
        this.communityName = communityName;
    }

    public Community(String communityName, Set<UserCommunity> usersCommunities) {
        this.communityName = communityName;
        this.usersCommunities = usersCommunities;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMUNITY_ID")
    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.community")
    public Set<UserCommunity> getUsersCommunities() {
        return usersCommunities;
    }

    public void setUsersCommunities(Set<UserCommunity> usersCommunities) {
        this.usersCommunities = usersCommunities;
    }

    //    public Set<User> getUsers() {
//        return users;
//    }
//
//    public void setUsers(Set<User> users) {
//        this.users = users;
//    }
}
