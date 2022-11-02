package com.ht.penitancier.services;

import com.ht.penitancier.dtos.PrisonDTO;

import java.util.List;

public interface IPrison {

     PrisonDTO add(PrisonDTO request) ;

     PrisonDTO update(PrisonDTO request, Long id) ;

     void changeStatus(Long id) ;

     List<PrisonDTO> prisons() ;

     Long count() ;
}
