package com.ht.penitancier.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ht.penitancier.configurations.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Slf4j
public class JWTAuthorizationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization") ;

        if ( authorization ==null || !authorization.startsWith(Configuration.KEY)) {
            log.warn("Bad not works!!!");
            filterChain.doFilter(request, response);
            return;
        }
        try {
            log.info("Good it works!!");
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(Configuration.SECRET_KEY)).build();

            String jwt = authorization.substring(Configuration.KEY_COUNT);
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt.trim());

            String username = decodedJWT.getSignature();

            List<String> roles = decodedJWT.getClaim("roles").asList(String.class);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            if (Objects.nonNull(roles)) {
                roles.forEach(role -> authorities.add(new SimpleGrantedAuthority(role)));
            }
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } catch (ServletException | IllegalArgumentException | JWTVerificationException | IOException ex) {
            response.addHeader("message", ex.getMessage());
        }
    }
}
