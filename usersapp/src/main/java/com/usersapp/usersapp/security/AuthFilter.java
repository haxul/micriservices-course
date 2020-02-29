package com.usersapp.usersapp.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersapp.usersapp.models.LoginRequestModel;
import com.usersapp.usersapp.services.UsersService;
import com.usersapp.usersapp.shared.UserDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

    private final UsersService userService;
    private final Environment environment;

    public AuthFilter(UsersService userService, Environment environment, AuthenticationManager authenticationManager ) {
        super.setAuthenticationManager(authenticationManager);
        this.environment = environment;
        this.userService = userService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            LoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(), LoginRequestModel.class);
            return getAuthenticationManager().
                    authenticate(new UsernamePasswordAuthenticationToken(creds.getEmail(),creds.getPassword(), new ArrayList<>()));
        } catch (IOException err) {
            throw new RuntimeException(err);
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication authentication) throws IOException, ServletException {

        String userName = ((User) authentication.getPrincipal()).getUsername();
        UserDto userDto = userService.getUsersDetailsByEmail(userName);

        String token = Jwts.builder()
                .setSubject(userDto.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret"))
                .compact();
        res.addHeader("token", token);
        res.addHeader("userId", String.valueOf(userDto.getUserId()));
    }
}
