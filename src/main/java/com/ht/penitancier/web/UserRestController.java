package com.ht.penitancier.web;

import com.ht.penitancier.models.Roles;
import com.ht.penitancier.models.Users;
import com.ht.penitancier.services.serviceImpl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/administration/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRestController  {

    private UserServiceImpl userService;


    @PostAuthorize("hasAuthority('JUGE')")
    @PostMapping(path = "roles/")
    public ResponseEntity<Roles> addRole(@Valid @RequestBody(required = true) Roles role) {
        return new ResponseEntity<>( this.userService.addRole(role),  HttpStatus.CREATED);
    }

    @PostAuthorize("hasAuthority('JUGE')")
    @PostMapping(path = "users/{username}/{role}")
    public ResponseEntity<Users> addUserToRole( @PathVariable("username") String username, @PathVariable("role") String role) {
        return new ResponseEntity<>( this.userService.addUserToRole(username, role),  HttpStatus.CREATED);
    }

    @PostAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'JUGE')")
    @GetMapping(path = "roles/")
    public ResponseEntity<List<Roles>> roles() {
        return new ResponseEntity<>( this.userService.roles(),  HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'JUGE')")
    @GetMapping(path = "users/{username}")
    public ResponseEntity<Users> findByUsername(@PathVariable("username") String username) {
        return new ResponseEntity<>( this.userService.findByUsername(username),  HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'JUGE')")
    @PostMapping(path = "users/")
    public ResponseEntity<Users> save(@Valid @RequestBody(required = true)Users user) {
        return new ResponseEntity<>( this.userService.save(user),  HttpStatus.CREATED);
    }

    @PostAuthorize("hasAuthority('JUGE')")
    @DeleteMapping(path = "users/")
    public void delete(Long id) {
        this.userService.delete(id);
    }

    @PostAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'JUGE')")
    @PatchMapping(path = "users/")
    public void enabled(Long id) {
        this.userService.enabled(id);
    }


    @PostAuthorize("hasAnyAuthority('ADMINISTRATEUR', 'JUGE')")
    @GetMapping(path = "users/")
    public ResponseEntity<List<Users>> findAll() {
        return new ResponseEntity<>( this.userService.findAll(),  HttpStatus.OK);
    }
}
