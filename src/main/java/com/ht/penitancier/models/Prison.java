package com.ht.penitancier.models;

import com.ht.penitancier.utils.enums.PrisonEtatType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Prison")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Prison implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id ;

    @Column(name = "name", length = 100, nullable = false)
    String name ;

    @Column(name = "department", length = 100, nullable = false)
    String department ;

    @Column(name = "city", length = 100, nullable = false)
    String city ;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Column(name = "date_enregistrement", nullable = false, updatable = false, insertable = true)
    Date created;

    @Column(name = "status", length = 100, nullable = false)
    @Enumerated(EnumType.STRING)
    PrisonEtatType status ;

    @Column(name = "roomNumbers", nullable = false)
    Integer roomNumbers;

    @Column(name = "guardNumbers", nullable = false)
    Integer guardNumbers ;

    @Column(name = "chef", length = 100, nullable = false)
    String chef ;

    @Column(name = "chef_phone", length = 15, nullable = false)
    String chef_phone ;

}
