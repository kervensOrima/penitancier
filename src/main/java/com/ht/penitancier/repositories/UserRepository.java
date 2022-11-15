package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    @Query(value = "select u from Users u where (u.username =:username)")
    Optional<Users> findByUsername(@Param("username") String username);


    Optional<Users> findByUsernameOrEmailOrCin(@Param("username") String username, @Param("email") String email, @Param("cin") String cin) ;


}
