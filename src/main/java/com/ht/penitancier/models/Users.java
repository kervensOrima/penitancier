package com.ht.penitancier.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Users")
public class Users extends Person implements Serializable {

     @Column(name = "code", unique = true, nullable = false, updatable = false, insertable = true)
     String code ;

     @Column(name = "username", nullable = false, unique = true, length = 100)
     String username ;

     @Column(name = "password", nullable = false, length = 255)
     String password ;

     @Column(name = "email", nullable = false, length = 100)
     String email ;

     boolean active ;

     @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
     @JoinTable( name = "User_Roles",
                  joinColumns = @JoinColumn(name = "idUser"),
                  inverseJoinColumns = @JoinColumn(name = "idRoles")
               )
     List<Roles> roles = new ArrayList<>();


}
