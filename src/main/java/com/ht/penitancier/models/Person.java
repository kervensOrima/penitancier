package com.ht.penitancier.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@ToString
@Getter
@Setter
@Entity
@Table(name = "Person")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Person implements Serializable {

     @Id
     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detenuSequence")
     @SequenceGenerator(name = "detenuSequence",  sequenceName = "detenuSequence", initialValue = 10000)
     Long id ;

     @Column(name = "first_name", length = 100, nullable = false)
     String firstName;

     @Column(name = "last_name", length = 100, nullable = false)
     String lastname;

     @Column(name = "genre", length = 10, nullable = false)
     String genre ;

     @Column(name = "phone_number", length = 15, nullable = false)
     String phoneNumber ;

     @Column(name = "birthDate")
     Date birthDate ;

     @Column(name = "cin", unique = true, nullable = false, updatable = true, length = 20)
     String cin ;
}
