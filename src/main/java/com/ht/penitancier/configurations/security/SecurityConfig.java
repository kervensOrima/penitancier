package com.ht.penitancier.configurations.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;

    BCryptPasswordEncoder passwordEncoder ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService)
                .passwordEncoder( this.passwordEncoder );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Desactivé la protection csrf
        http.csrf()
                .disable() ;

        // Utiliser la methode de securité stateless
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) ;

        http.authorizeRequests().antMatchers("/login/**").permitAll();
        
        // autorise la route /login par tous
        http.authorizeRequests().antMatchers(
                "/v2/api-docs",
                "/swagger-resources",
                "/swagger-resources/**",
                "/configuration/ui",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                // -- Swagger UI v3 (OpenAPI)
                "/v3/api-docs/**",
                "/swagger-ui/**"
        ).permitAll() ;


        http.authorizeRequests().anyRequest().authenticated();
        
        // Ajouter un filter authentication
        http.addFilter(new JWTAuthenticationFilter(this.authenticationManager())) ;

        // ajouter le filter authorization
        http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class ) ;

    }
}
