package com.example.addGlobalPower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(-1)
public class CorsConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.requestMatchers().antMatchers(HttpMethod.OPTIONS,
                "/oauth/token", "/**")
            .and()
                .csrf().disable()
            .authorizeRequests().anyRequest().permitAll();
    }
}
