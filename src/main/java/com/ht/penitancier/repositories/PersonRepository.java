package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Person;
import com.ht.penitancier.utils.enums.StatusDetenuType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

    Optional<Person> findByCin(String cin);

    @Query(value = "select d from Detenu d where d.prison.id = :prison")
    Page<Person> all(Pageable pageable, @Param("prison") Long prison) ;

    @Query(value = "select d from Detenu d where d.prison.id =:prison and d.status =:status")
    Page<Person> all(Pageable pageable,@Param("status") StatusDetenuType status, @Param("prison") Long prison);

    @Query(value = "select count(d) from Detenu d where d.prison.id = :prison")
    Long totalByPrison(@Param("prison") Long prison) ;

    @Modifying
    @Query(value = "update Detenu d set d.status = :status where d.id = :id")
    void changeStatusDetenu(@Param("id") Long idDetenu, @Param("status") StatusDetenuType status);
}
