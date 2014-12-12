package org.softserveinc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HibernateDAO hibernateDAO;

    /**
     * Returns a populated {@link org.springframework.security.core.userdetails.UserDetails} object.
     * The username is first retrieved from the database and then mapped to
     * a {@link org.springframework.security.core.userdetails.UserDetails} object.
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        org.softserveinc.domain.User domainUser = hibernateDAO.findUserByUsername(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                domainUser.getUsername(),
                domainUser.getPassword().toLowerCase(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getAuthorities(domainUser.getRole().getRole()));
    }

    /**
     * Retrieves a collection of {@link org.springframework.security.core.GrantedAuthority} based on a numerical role
     * @param role the numerical role
     * @return a collection of {@link org.springframework.security.core.GrantedAuthority
     */
    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
        return authList;
    }

    public List<String> getRoles(Integer role) {
        List<String> roles = new ArrayList<String>();

        switch (role.intValue()){
            case 1:
                roles.add("ROLE_ADMIN");
                break;

            case 2:
                roles.add("ROLE_USER");
                break;

            default:
                roles.add("ROLE_ANONYMOUS");
                break;
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }

}
