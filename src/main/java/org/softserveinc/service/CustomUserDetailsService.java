package org.softserveinc.service;

import org.softserveinc.domain.User;
import org.softserveinc.domain.UserRole;
import org.softserveinc.repository.HibernateDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = hibernateDAO.findUserByUsername(username);
        setAuthorities(user);
        return user;
    }

    private static void setAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        Iterator<UserRole> roleIterator = user.getRoles().iterator();
        while (roleIterator.hasNext()) {
            authorities.add(new SimpleGrantedAuthority(roleIterator.next().getRole()));
        }
        user.setAuthorities(authorities);
    }

}
