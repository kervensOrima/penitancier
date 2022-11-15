package com.ht.penitancier.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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

     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     @Column(name = "password", nullable = false, length = 255)
     String password ;

     @Column(name = "email", nullable = false, length = 100)
     String email ;

     boolean enable ;

     @ManyToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
     @JoinTable( name = "User_Roles",
                  joinColumns = @JoinColumn(name = "idUser"),
                  inverseJoinColumns = @JoinColumn(name = "idRoles")
               )
     List<Roles> roles = new ArrayList<>();


     public Users(Long id, String firstName, String lastname, String genre, String phoneNumber, Date birthDate, String cin, String code, String username, String password, String email, boolean enable, List<Roles> roles) {
          super(id, firstName, lastname, genre, phoneNumber, birthDate, cin);
          this.code = code;
          this.username = username;
          this.password = password;
          this.email = email;
          this.enable = enable;
          this.roles = roles;
     }
}
