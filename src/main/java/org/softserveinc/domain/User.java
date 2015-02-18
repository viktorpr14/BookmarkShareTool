package org.softserveinc.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Entity
@Table(name = "USER")
public class User implements UserDetails {

    protected Integer userId;

    @NotNull
    protected String firstName;

    @NotNull
    protected String lastName;

    @NotNull
    @Column(unique = true)
    protected String username;

    @Column
    protected String email;

    @NotNull
    protected String password;

    protected Set<UserRole> roles = new HashSet<UserRole>();

    private List<? extends GrantedAuthority> authorities;

    private transient boolean accountNonExpired;
    private transient boolean accountNonLocked;
    private transient boolean credentialsNonExpired;
    private transient boolean enabled;

    public User() {
    }

    public User(String firstName, String lastName, String username, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
            joinColumns = @JoinColumn(name="userId"),
            inverseJoinColumns = @JoinColumn(name="roleId"))
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return true;
    }
    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired=accountNonExpired;
    }

    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return true;
    }
    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return true;
    }
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    @Transient
    public boolean isEnabled() {
        return true;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    @Transient
    public List<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
