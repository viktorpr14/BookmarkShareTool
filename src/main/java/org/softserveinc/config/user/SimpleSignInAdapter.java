package org.softserveinc.config.user;

import org.softserveinc.domain.ProviderUserLocalUser;
import org.softserveinc.domain.User;
import org.softserveinc.domain.UserRole;
import org.softserveinc.service.UserService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
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

    public SimpleSignInAdapter() {
    }

    public SimpleSignInAdapter(UserService userService) {
        this.userService = userService;
    }

    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {

        User user = null;

        String providerId = connection.getKey().getProviderId();
        String providerUserId = connection.getKey().getProviderUserId();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {

            user = (User) authentication.getPrincipal();
            linkUserWithSocialProvider(user, providerId, providerUserId);

            return  null;
        }

        //Check if provider user already exists
        ProviderUserLocalUser providerUserLocalUser =
                userService.getProviderUserLocalUserByProvIdAndProvUserId(providerId, providerUserId);

        if (!(providerUserLocalUser == null)) {
            user = userService.getUserById(providerUserLocalUser.getLocalUserId());
        }

        if (user == null) {

            if (connection.getApi() instanceof Google) {
                user = createUserFromGoogleProfile(connection, providerId, providerUserId);
            }

            if (connection.getApi() instanceof Facebook) {
                user = createUserFromFacebookProfile(connection, providerId, providerUserId);
            }
        }

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        return null;
    }

    private User createUserFromGoogleProfile(Connection<?> connection, String providerId, String providerUserId) {
        Google google = (Google) connection.getApi();
        Person person = google.plusOperations().getGoogleProfile();

        //Check if user with the same email already exists
        User user = userService.getUserByEmail(person.getAccountEmail());

        if(user == null) {

            user = new User();
            user.setEmail(person.getAccountEmail());
            user.setFirstName(person.getGivenName());
            user.setLastName(person.getFamilyName());
            user.setRoles(new HashSet<UserRole>() {{add(new UserRole(2, "ROLE_USER"));}});
            user.setUsername(person.getGivenName() + person.getFamilyName());
            user.setPassword("0000");

            userService.saveUserIntoDB(user);
        }

        saveProviderUserLocalUserIntoDB(providerId, providerUserId, user.getUserId().toString());

        return user;
    }

    private User createUserFromFacebookProfile(Connection<?> connection, String providerId, String providerUserId) {
        Facebook facebook = (Facebook) connection.getApi();
        FacebookProfile facebookProfile = facebook.userOperations().getUserProfile();

        //Check if user with the same email already exists
        User user = userService.getUserByEmail(facebookProfile.getEmail());

        if(user == null) {

            user = new User();
            user.setEmail(facebookProfile.getEmail());
            user.setFirstName(facebookProfile.getFirstName());
            user.setLastName(facebookProfile.getLastName());
            user.setRoles(new HashSet<UserRole>() {{add(new UserRole(2, "ROLE_USER"));}});
            user.setUsername(facebookProfile.getFirstName() + facebookProfile.getLastName());
            user.setPassword("0000");

            userService.saveUserIntoDB(user);
        }

        saveProviderUserLocalUserIntoDB(providerId, providerUserId, user.getUserId().toString());

        return user;
    }

    private void saveProviderUserLocalUserIntoDB(String providerId, String providerUserId, String userId) {
        ProviderUserLocalUser providerUserLocalUserInner = new ProviderUserLocalUser();

        providerUserLocalUserInner.setProviderId(providerId);
        providerUserLocalUserInner.setProviderUserId(providerUserId);
        providerUserLocalUserInner.setLocalUserId(userId);

        userService.saveProviderUserLocalUser(providerUserLocalUserInner);
    }

    private void linkUserWithSocialProvider(User user, String providerId, String providerUserId) {
        ProviderUserLocalUser providerUserLocalUser =
                userService.getProviderUserLocalUserByProvIdAndProvUserId(providerId, providerUserId);

        if (!(providerUserLocalUser == null)) {

            providerUserLocalUser.setLocalUserId(user.getUserId().toString());
            userService.updateProviderUserLocalUser(providerUserLocalUser);

        } else {
            saveProviderUserLocalUserIntoDB(providerId, providerUserId, user.getUserId().toString());
        }
    }

}
