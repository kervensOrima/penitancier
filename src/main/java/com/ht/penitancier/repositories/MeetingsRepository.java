package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Meetings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.Optional;

@Repository
public interface MeetingsRepository extends JpaRepository<Meetings, Long> {

    @Query(value = "select m from Meetings  m where m.detenu.id =:idDetenu")
    Page<Meetings> all(Pageable pageable , @Param("idDetenu") Long idDetenu) ;


    @Query(value = "select count(m) from Meetings m where m.detenu.id =:id and substring(m.startMeeting, 1, 4)  =:year")
    Long meetings(@Param("id") Long id, @Param("year") String year ) ;

    @Query(value = "select m from Meetings m where m.detenu.id =:id  and m.startMeeting =:date")
    Optional<Meetings>  findMeetingsByDetenu(@Param("id") Long id, @Param("date") Date date) ;
}
