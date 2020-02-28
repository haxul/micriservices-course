package com.zull.zull.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class  AuthFilter extends BasicAuthenticationFilter {

    private Environment environment;

    public AuthFilter(AuthenticationManager authenticationManager, Environment environment) {
        super(authenticationManager);
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || authHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthToken(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null) {
            return null;
        }
        String token = authHeader.replace("Bearer", "");
        String userId = Jwts.parser()
                .setSigningKey(environment.getProperty("token.secret"))
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        if (userId == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(userId, null, new ArrayList<>());

    }

}
