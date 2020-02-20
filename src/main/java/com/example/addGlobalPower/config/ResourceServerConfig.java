package com.example.addGlobalPower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by Laura on 5/5/2018.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String USERS_SECURED_PATTERN = "/users/**";
    private static final String ROLES_SECURED_PATTERN = "/roles/**";
    private static final String USERS_SECURED_INFO = "/userinfo";
    private static final String USERS_SECURED_ME = "/me";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .anonymous().disable()
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**", "/api-docs/**").permitAll()
                .antMatchers(USERS_SECURED_PATTERN, ROLES_SECURED_PATTERN).hasAuthority("ADMIN");
    }
}
