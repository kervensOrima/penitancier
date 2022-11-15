package com.ht.penitancier.services.serviceImpl;

import com.ht.penitancier.models.Roles;
import com.ht.penitancier.models.Users;
import com.ht.penitancier.repositories.RoleRepository;
import com.ht.penitancier.repositories.UserRepository;
import com.ht.penitancier.services.IRoles;
import com.ht.penitancier.services.IUser;
import com.ht.penitancier.services.exceptions.AlreadyReported;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements IUser, IRoles {

    RoleRepository roleRepository;

    UserRepository userRepository;

    BCryptPasswordEncoder passwordEncoder ;

    @Override
    public Roles addRole(Roles role) {
        return this.roleRepository.save(role);
    }

    @Override
    public List<Roles> roles() {
        return this.roleRepository.findAll();
    }

    @Override
    public Users addUserToRole(String username, String r) {
        Users user = this.findByUsername(username);
        Roles role = this.roleRepository
                .findByRole(r)
                .orElseThrow(() -> new ObjectNotFoundException("role not found exception!"));
        // add the role to the user
        user.getRoles().add(role);
        return user;
    }

    @Override
    public Users findByUsername(String username) {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new ObjectNotFoundException("username not found exception"));
    }

    @Override
    public Users save(Users user) {
        // check username or email
        Optional<Users> u = this.userRepository
                .findByUsernameOrEmailOrCin(user.getUsername(), user.getEmail(), user.getCin()) ;
        if(u.isPresent()) {
            throw new AlreadyReported("username or email or cin is invalid, try with another username") ;
        }

        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        this.userRepository.deleteById(id);
    }

    @Override
    public void enabled(Long id) {
        Users user = this.userRepository.findById(id)
                .orElseThrow(() ->
                        new ObjectNotFoundException("user not found, error to disable in existing user!"));
        user.setEnable(!user.isEnable());
    }

    @Override
    public List<Users> findAll() {
        return this.userRepository.findAll();
    }
}
