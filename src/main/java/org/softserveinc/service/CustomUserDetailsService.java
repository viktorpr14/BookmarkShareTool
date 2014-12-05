package org.softserveinc.service;

import org.softserveinc.domain.UserRole;
import org.softserveinc.repository.HibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        org.softserveinc.domain.User domainUser = hibernateDAO.findUserByUsername(username);

        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        return new User(
                domainUser.getUsername(),
                domainUser.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                getGrantedAuthorities(domainUser.getRoles()));
    }

    public static List<GrantedAuthority> getGrantedAuthorities(Collection<UserRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Iterator<UserRole> roleIterator = roles.iterator();
        while (roleIterator.hasNext()) {
            authorities.add(new SimpleGrantedAuthority(roleIterator.next().getRole()));
        }
        return authorities;
    }

}
