package org.softserveinc.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
@Table(name = "USER")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @Column
    private String email;

    @NotNull
    private String password;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name="USERS_ROLES",
//        joinColumns = @JoinColumn(name="USER_ID"),
//        inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
    private Set<UserRole> roles = new HashSet<UserRole>();

//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    @NotFound(action = NotFoundAction.IGNORE)
//    @JoinTable(name="USERS_COMMUNITIES",
//        joinColumns = @JoinColumn(name="USER_ID"),
//        inverseJoinColumns = @JoinColumn(name="COMMUNITY_ID"))
//    private Set<Community> communities = new HashSet<Community>();

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.user", cascade = CascadeType.ALL)
    private Set<UserCommunity> usersCommunities = new HashSet<UserCommunity>();

    public User() {
    }

    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="USERS_ROLES",
            joinColumns = @JoinColumn(name="USER_ID"),
            inverseJoinColumns = @JoinColumn(name="ROLE_ID"))
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "pk.user", cascade = CascadeType.ALL)
    public Set<UserCommunity> getUsersCommunities() {
        return usersCommunities;
    }

    public void setUsersCommunities(Set<UserCommunity> usersCommunities) {
        this.usersCommunities = usersCommunities;
     }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
