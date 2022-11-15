package com.ht.penitancier.configurations.security;


import com.ht.penitancier.models.Users;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import com.ht.penitancier.services.exceptions.UserNotFoundRole;
import com.ht.penitancier.services.serviceImpl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@AllArgsConstructor
@Service
@Transactional
public class UserDetailImpl implements UserDetailsService {

    UserServiceImpl userService ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.userService.findByUsername(username) ;
        System.out.println("======= loadUserByUsername =========== " + user.toString() );

        if(!user.isEnable()) {
            throw new ObjectNotFoundException("this account is disabled, you cannot connect now! try later!") ;
        }

        Collection<GrantedAuthority>  authorities = new ArrayList<>() ;
        if(user.getRoles().isEmpty()) {
            throw new UserNotFoundRole(user.getUsername() + " has no roles, this is an in-authorize request!") ;
        }

        user.getRoles().forEach( role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole())) ;
        });
        return new User(user.getUsername(), user.getPassword(), authorities);
    }
}
