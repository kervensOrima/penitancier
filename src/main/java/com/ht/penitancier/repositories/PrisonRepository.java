package com.ht.penitancier.repositories;

import com.ht.penitancier.models.Prison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrisonRepository extends JpaRepository<Prison, Long> {

}
