package com.ht.penitancier.services;

import com.ht.penitancier.dtos.PrisonDTO;

import java.util.List;

public interface IPrison {

     /*
     * register new prison
     * */
     PrisonDTO add(PrisonDTO request) ;

     /*
      * update information about a  prison
      * */
     PrisonDTO update(PrisonDTO request, Long id) ;

     /*
      * find one by id prison
      * */
     PrisonDTO findById(Long id) ;

     /*
      * change prison status
      * */
     void changeStatus(Long id) ;

     /*
      * find all prison
      * */
     List<PrisonDTO> prisons() ;

     /*
      * number of prison
      * */
     Long count() ;
}
