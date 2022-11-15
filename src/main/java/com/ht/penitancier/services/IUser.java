package com.ht.penitancier.services;

import com.ht.penitancier.models.Users;

import java.util.List;

public interface IUser {

    Users findByUsername(String username) ;

    Users save(Users user) ;

    void delete(Long id);

    void enabled(Long id);

    List<Users> findAll();

}
