package org.softserveinc.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class GeneralConfig {

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }




    @Bean
    public BasicDataSource dataSource(
            @Value("${app.jdbc.driverClassName}") String driverClassName,
            @Value("${app.jdbc.url}") String url,
            @Value("${app.jdbc.username}") String username,
            @Value("${app.jdbc.password}") String password,
            @Value("${app.jdbc.initialSize}") int initialSize,
            @Value("${app.jdbc.maxActive}") int maxActive) {

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMaxActive(maxActive);

        return dataSource;
    }
}
