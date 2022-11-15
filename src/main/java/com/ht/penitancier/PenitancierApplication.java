package com.ht.penitancier;

import com.ht.penitancier.models.Roles;
import com.ht.penitancier.models.Users;
import com.ht.penitancier.services.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class PenitancierApplication {

    public static void main(String[] args) {
        SpringApplication.run(PenitancierApplication.class, args);
    }

    @Autowired
    UserServiceImpl userService ;


     @Bean
    public CommandLineRunner start(){
        return args -> {

//             System.out.println(userService.findByUsername("ocodeur"));

//            Roles role1 = new Roles(null, "JUGE") ;
//            Roles role2 = new Roles(null, "POLICIER_APENAH") ;
//
//            Roles role3 = new Roles(null, "DIRECTEUR") ;
//
//            Roles role4 = new Roles(null, "ADMINISTRATEUR") ;
//
//
//             userService.addRole(role1) ;
//            userService.addRole(role2) ;
//            userService.addRole(role3) ;
//            userService.addRole(role4) ;
//
//
//            Users user1 = new Users(null, "ORIMA", "Kervens", "MALE", "(509)37347727", new Date(), "000-939-930",
//                    UUID.randomUUID().toString(), "ocodeur", "12345", "kervensorima0@gmail.com", true, null) ;
//
//            Users user2 = new Users(null, "ORIMA", "policier", "MALE", "(509)37347728", new Date(), "000-939-931",
//                    UUID.randomUUID().toString(), "ocodeur2", "12345", "policier@gmail.com", true, null) ;
//
//            Users user3 = new Users(null, "ORIMA", "directeur", "MALE", "(509)37347729", new Date(), "000-939-933",
//                    UUID.randomUUID().toString(), "ocodeur3", "12345", "directeur@gmail.com", true, null) ;
//
//            Users user4 = new Users(null, "ORIMA", "administrateur", "MALE", "(509)37347720", new Date(), "000-939-993",
//                    UUID.randomUUID().toString(), "ocodeur4", "12345", "administrateur@gmail.com", true, null) ;
//
//            userService.save(user1) ;
//            userService.save(user2) ;
//            userService.save(user3) ;
//            userService.save(user4) ;
//
//            userService.addUserToRole(user1.getUsername(), role1.getRole()) ;
//            userService.addUserToRole(user1.getUsername(), role2.getRole()) ;
//            userService.addUserToRole(user1.getUsername(), role3.getRole()) ;
//            userService.addUserToRole(user1.getUsername(), role4.getRole()) ;
//
//
//            userService.addUserToRole(user2.getUsername(), role2.getRole()) ;
//            userService.addUserToRole(user3.getUsername(), role3.getRole()) ;
//            userService.addUserToRole(user4.getUsername(), role4.getRole()) ;



        } ;
    }
}
