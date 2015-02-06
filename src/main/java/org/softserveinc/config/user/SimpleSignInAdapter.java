package org.softserveinc.config.user;

import org.softserveinc.domain.User;
import org.softserveinc.domain.UserRole;
import org.softserveinc.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.plus.Person;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.HashSet;

/**
 * Created by vv on 03.02.2015.
 */
public final class SimpleSignInAdapter implements SignInAdapter {

    private UserService userService;

    public SimpleSignInAdapter() {}

    public SimpleSignInAdapter(UserService userService) {
        this.userService = userService;
    }

    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {

        Google google =(Google) connection.getApi();
        Person person = google.plusOperations().getGoogleProfile();

        //save user into DB
        User user = new User();
        user.setEmail(person.getAccountEmail());
        user.setFirstName(person.getGivenName());
        user.setLastName(person.getFamilyName());
        user.setRoles(new HashSet<UserRole>(){{add(new UserRole(2, "ROLE_USER"));}});
        user.setUsername(person.getGivenName() + person.getFamilyName());
        user.setPassword("0000");

        userService.saveUserIntoDB(user);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return null;
    }

}
