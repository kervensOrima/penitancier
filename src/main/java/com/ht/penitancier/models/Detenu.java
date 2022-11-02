package com.ht.penitancier.models;

import com.ht.penitancier.utils.enums.StatusDetenuType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "Detenu")
@Entity
public class Detenu extends Person implements Serializable {


    @Column(unique = true, length = 255, nullable = false)
    String code ;

    @Column(name = "alias", nullable = true)
    String alias ;

    @Column(name = "date_emprisonnement", nullable = false)
    Date dateOfImprisonment ;

    @Column(name = "liberation_Date", nullable = false)
    Date liberation_Date ;

    @Column(name = "date_Judgment", nullable = false)
    Date date_Judgment ;

    @Enumerated(value = EnumType.STRING)
    StatusDetenuType status ;

    @OneToMany(mappedBy = "detenu", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<Meetings> meetings = new ArrayList<>( );

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    Prison prison ;
}
