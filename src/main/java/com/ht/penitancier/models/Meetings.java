package com.ht.penitancier.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "Meetings")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Meetings implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meetings_id")
    Long id;

    String raison ;

    Date startMeeting ;

    Date endMeeting ;

    String visitor_name ;

    String visitor_cin ;

    String visitor_phone ;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_detenu", nullable = false )
    Detenu detenu ;

}
