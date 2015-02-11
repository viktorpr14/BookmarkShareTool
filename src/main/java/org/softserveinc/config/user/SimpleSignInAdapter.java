package org.softserveinc.config.user;

import org.softserveinc.domain.ProviderUserLocalUser;
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
 * Adapter that bridges between a ProviderSignInController and a application-specific user sign-in service.
 * Invoked at the end of a provider sign-in attempt to sign-in the local user account associated with the provider user account.
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

        User user = null;

        String providerId = connection.getKey().getProviderId();
        String providerUserId = connection.getKey().getProviderUserId();

        //Check if provider user already exists
        ProviderUserLocalUser providerUserLocalUser =  userService.getProviderUserLocalUserByProvIdAndProvUserId(providerId, providerUserId);

        if (!(providerUserLocalUser == null)) {
            user = userService.getUserById(providerUserLocalUser.getLocalUserId());
        }

        if (user == null) {

            //save user into DB
            user = new User();
            user.setEmail(person.getAccountEmail());
            user.setFirstName(person.getGivenName());
            user.setLastName(person.getFamilyName());
            user.setRoles(new HashSet<UserRole>() {{add(new UserRole(2, "ROLE_USER"));}});
            user.setUsername(person.getGivenName() + person.getFamilyName());
            user.setPassword("0000");

            userService.saveUserIntoDB(user);

            //save relationship between provider user and local user
            ProviderUserLocalUser providerUserLocalUserInner = new ProviderUserLocalUser();
            providerUserLocalUserInner.setProviderId(providerId);
            providerUserLocalUserInner.setProviderUserId(providerUserId);
            providerUserLocalUserInner.setLocalUserId(user.getUserId().toString());

            userService.saveProviderUserLocalUser(providerUserLocalUserInner);
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return null;
    }

}
