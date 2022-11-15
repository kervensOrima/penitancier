package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Prison;
import com.ht.penitancier.utils.enums.PrisonEtatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisonRepository extends JpaRepository<Prison, Long> {

    @Modifying
    @Query(name = "changeStatus", value = "update Prison p set p.status =:status where p.id =:id ", nativeQuery = false)
    void changeStatus(@Param("status") PrisonEtatType status, @Param("id") Long id) ;

}
