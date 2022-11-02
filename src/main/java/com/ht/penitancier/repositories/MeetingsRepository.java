package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Meetings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingsRepository extends JpaRepository<Meetings, Long> {

}
