package com.ht.penitancier.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "ROLES")
@Builder
public class Roles implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long idRole;

    @Column(name = "role", nullable = false, unique = true, length = 100)
    String role;
}
