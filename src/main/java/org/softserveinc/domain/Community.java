package org.softserveinc.domain;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by vv on 05.12.2014.
 */
@Entity
@Table(name = "COMMUNITY")
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer communityId;

    private String communityName;

    @ManyToMany(mappedBy = "communities")
    private Collection<User> users;

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

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
