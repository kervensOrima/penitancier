package com.ht.penitancier.services;

import com.ht.penitancier.dtos.DetenuDTO;
import com.ht.penitancier.models.Prison;
import com.ht.penitancier.utils.enums.StatusDetenuType;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDetenu {

    /*
    *
    *  save new person in the prison
    * */
    DetenuDTO save(DetenuDTO request) ;

    /*
    *
    * get information about a person by it id
    * */
    DetenuDTO get(Long id) ;

    /*
     * update detenu
     * */
    DetenuDTO update(DetenuDTO request, Long id) ;

    /**
     * list of the detenu register
     * */
    List<DetenuDTO> all();

    /**
     *
     * get list of detenu by prison
     * */
    Page<DetenuDTO> all(Long prisonId, int page, int size);

    /*
     *
     * get detenu by status
     * **/
    Page<DetenuDTO> all(StatusDetenuType status, Long pisonId, int page, int size);


    /*
    * change the status for a detenu
    * */
    void changeStatusDetenu(Long idDetenu, StatusDetenuType status) ;

    /*
    * total detenu register
    * */
    Long total();

    /*
    *  number of detenu by prison
    * */
    Long totalByPrison(Long idPrison) ;

}
