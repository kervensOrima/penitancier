package com.ht.penitancier.services;

import com.ht.penitancier.models.Roles;
import com.ht.penitancier.models.Users;

import java.util.List;

public interface IRoles {

    Roles addRole(Roles role) ;

    Users addUserToRole(String username, String role) ;

    List<Roles> roles() ;

}
