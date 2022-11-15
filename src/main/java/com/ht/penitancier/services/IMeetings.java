package com.ht.penitancier.services;

import com.ht.penitancier.dtos.MeetingsDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IMeetings {

    MeetingsDTO save(MeetingsDTO request, Long detenu) ;

    MeetingsDTO update(MeetingsDTO request, Long detenu) ;

    Page<MeetingsDTO> meetings(int page, int size, Long detenuId) ;

    List<MeetingsDTO> meetings() ;

    Long meetings(Long detenuId, Long year) ;

    Long meetings(Long detenuId) ;

    MeetingsDTO get(Long meetingsId) ;

}
