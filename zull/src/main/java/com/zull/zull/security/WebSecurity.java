package com.zull.zull.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final Environment environment;

    public WebSecurity(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf();
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                .antMatchers("jdbc:mysql://localhost:3306/cuba").permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("signup.path")).permitAll()
                .antMatchers(HttpMethod.POST, environment.getProperty("login.path")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthFilter(authenticationManager(), environment));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
