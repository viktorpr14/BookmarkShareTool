package org.softserveinc.config;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.softserveinc.config.user.SimpleConnectionSignUp;
import org.softserveinc.config.user.SimpleSignInAdapter;
import org.softserveinc.domain.User;
import org.softserveinc.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
//import org.springframework.core.env.Environment;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.NotConnectedException;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.connect.GoogleConnectionFactory;

/**
 * Spring Social Configuration.
 */
@Configuration
public class SocialConfig {

    @Inject
    private UserService userService;

//    @Inject
//    private Environment environment;

    @Inject
    private DataSource dataSource;

    @Inject
    private ConnectionFactoryLocator connectionFactoryLocator;

    /**
     * When a new provider is added to the app, register its {@link ConnectionFactory} here.
     * @see GoogleConnectionFactory
     */
    @Bean
    public ConnectionFactoryLocator connectionFactoryLocator(
            @Value ("${google.clientId}") String googleClientId,
            @Value ("${google.clientSecret}") String googleClientSecret,
            @Value ("${facebook.clientId}") String facebookClientId,
            @Value ("${facebook.clientSecret}") String facebookClientSecret) {

        ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();

        registry.addConnectionFactory(new GoogleConnectionFactory(googleClientId, googleClientSecret));
        registry.addConnectionFactory(new FacebookConnectionFactory(facebookClientId, facebookClientSecret));

        return registry;
    }

    /**
     * Singleton data access object providing access to connections across all users.
     */
    @Bean
    public UsersConnectionRepository usersConnectionRepository() {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource,
                connectionFactoryLocator, Encryptors.noOpText());
        repository.setConnectionSignUp(new SimpleConnectionSignUp());
        return repository;
    }

    /**
     * Request-scoped data access object providing access to the current user's connections.
     */
    @Bean
    @Scope(value="session", proxyMode=ScopedProxyMode.INTERFACES)
    public ConnectionRepository connectionRepository() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usersConnectionRepository().createConnectionRepository(user.getUserId().toString());
    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Facebook account.
     * @throws NotConnectedException if the user is not connected to facebook.
     */
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Google google() {
        return connectionRepository().getPrimaryConnection(Google.class).getApi();
    }

    /**
     * A proxy to a request-scoped object representing the current user's primary Facebook account.
     * @throws NotConnectedException if the user is not connected to facebook.
     */
    @Bean
    @Scope(value="request", proxyMode=ScopedProxyMode.INTERFACES)
    public Facebook facebook() {
        return connectionRepository().getPrimaryConnection(Facebook.class).getApi();
    }

    /**
     * The Spring MVC Controller that allows users to sign-in with their provider accounts.
     */
    @Bean
    public ProviderSignInController providerSignInController() {
        ProviderSignInController controller = new ProviderSignInController(connectionFactoryLocator,
                usersConnectionRepository(), simpleSignInAdapter());
        return controller;
    }

    @Bean
    public SimpleSignInAdapter simpleSignInAdapter() {
        return new SimpleSignInAdapter(userService);
    }

}
