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


    @Column(unique = true, length = 255, nullable = false, updatable = false)
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
    @Column(name = "status_detenu", nullable = false, length = 25)
    StatusDetenuType status ;

    @Column(name = "image_url", length = 255, updatable = false, insertable = true )
    String imageUrl ;

    @OneToMany(mappedBy = "detenu", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    List<Meetings> meetings = new ArrayList<>( );

    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, insertable = true, foreignKey = @ForeignKey(name = "prison_fk") )
    Prison prison ;

    public Detenu(Long id, String firstName, String lastname, String genre, String phoneNumber, Date birthDate, String cin,
                  String code, String alias, Date dateOfImprisonment, Date liberation_Date, Date date_Judgment, StatusDetenuType status,
                  String imageUrl, List<Meetings> meetings, Prison prison) {
        super(id, firstName, lastname, genre, phoneNumber, birthDate, cin);
        this.code = code;
        this.alias = alias;
        this.dateOfImprisonment = dateOfImprisonment;
        this.liberation_Date = liberation_Date;
        this.date_Judgment = date_Judgment;
        this.status = status;
        this.imageUrl = imageUrl;
        this.meetings = meetings;
        this.prison = prison;
    }
}
