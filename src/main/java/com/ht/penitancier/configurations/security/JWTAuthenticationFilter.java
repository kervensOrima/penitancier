package com.ht.penitancier.configurations.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ht.penitancier.configurations.Configuration;
import com.ht.penitancier.models.Users;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    AuthenticationManager authenticationManager ;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("============== attemptAuthentication =========== ");
        Users user = null ;
        try {
            user = new ObjectMapper().readValue(request.getInputStream() , Users.class ) ;
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert user!=null ;
        return this.authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())) ;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        User spring_user = (User) authResult.getPrincipal();

        List<String> roles  = new ArrayList<>() ;
        spring_user.getAuthorities().forEach( authorities ->  roles.add(authorities.getAuthority()) );

        String jwt = JWT.create()
                .withSubject(spring_user.getUsername())
                .withArrayClaim("roles", roles.toArray(new String[roles.size()]))
                .withIssuedAt(new Date())
                .withIssuer(request.getContextPath())
                .withExpiresAt(new Date(System.currentTimeMillis() * 10 * 24 * 60 * 60 * 1000)) // 30 minute before expire
                .sign(Algorithm.HMAC256(Configuration.SECRET_KEY)) ;

        response.addHeader("Authorization", jwt );
    }
}
