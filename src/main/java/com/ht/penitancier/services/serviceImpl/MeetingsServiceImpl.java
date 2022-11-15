package com.ht.penitancier.services.serviceImpl;

import com.ht.penitancier.dtos.MeetingsDTO;
import com.ht.penitancier.models.Detenu;
import com.ht.penitancier.models.Meetings;
import com.ht.penitancier.repositories.MeetingsRepository;
import com.ht.penitancier.services.IMeetings;
import com.ht.penitancier.services.exceptions.AlreadyReported;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import com.ht.penitancier.services.exceptions.YearInvalidException;
import com.ht.penitancier.utils.DateUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
@AllArgsConstructor
public class MeetingsServiceImpl implements IMeetings {

    ModelMapper modelMapper ;

    MeetingsRepository meetingsRepository ;

    DetenuServiceImpl detenuService ;

    private Pageable page(int page , int size) {
       return PageRequest.of(page, size, Sort.by("startMeeting", "endMeeting").ascending()) ;
    }



    @Override
    public MeetingsDTO save(MeetingsDTO request, Long detenu) {
        if ((DateUtils.getYear(request.getStartMeeting()) < DateUtils.getYear(new Date())) ||
                (DateUtils.getYear(request.getEndMeeting()) < DateUtils.getYear(request.getStartMeeting()))) {
            throw new YearInvalidException("Meetings date is invalid!!");
        }
        Meetings m = null ;

        try {
            m = this.meetingsRepository.findMeetingsByDetenu(detenu, request.getStartMeeting())
                    .orElseThrow(()-> new ObjectNotFoundException("query failed, object not found")) ;
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
        if(! Objects.equals(m, null)) {
           throw new  AlreadyReported("detenu already have a meeting for today! no more than one meeting by day!");
        }
        // check if today is the only date, the detenu has a meetings
        boolean check_time = DateUtils.compareTime(request.getStartMeeting()) ;
        System.out.println("Check time result : " +  check_time );

        if (check_time) {
           throw new AlreadyReported("detenu already have a meeting today! no more than for a day");
        }
        Detenu d = this.modelMapper.map(this.detenuService.get(detenu), Detenu.class ) ;
        Meetings meetings = this.modelMapper.map(request, Meetings.class) ;
        meetings.setDetenu(d);
        return this.modelMapper.map(this.meetingsRepository.save(meetings), MeetingsDTO.class ) ;
    }

    @Override
    public MeetingsDTO update(MeetingsDTO request, Long detenu) {
        this.get(request.getId()) ;
        Detenu d = this.modelMapper.map(this.detenuService.get(detenu), Detenu.class ) ;
        Meetings meetings = this.modelMapper.map(request, Meetings.class) ;
        meetings.setDetenu(d) ;
        return this.modelMapper
                .map( this.meetingsRepository.save(meetings), MeetingsDTO.class);
    }

    @Override
    public Page<MeetingsDTO> meetings(int page, int size, Long detenuId) {
        return this.meetingsRepository
                .all(this.page(page, size), detenuId)
                .map( m -> this.modelMapper.map(m, MeetingsDTO.class));
    }

    @Override
    public List<MeetingsDTO> meetings() {
        return this.meetingsRepository
                .findAll()
                .stream()
                .map( m -> this.modelMapper.map(m, MeetingsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Long meetings(Long detenuId, Long year) {
        return this.meetingsRepository.meetings(detenuId, String.valueOf(year));
    }

    @Override
    public Long meetings(Long detenuId) {
        return this.meetingsRepository.count();
    }

    @Override
    public MeetingsDTO get(Long meetingsId) {
        return this.modelMapper.map(
                        this.meetingsRepository
                                .findById(meetingsId)
                                .orElseThrow(() -> new ObjectNotFoundException("meeting not found")), MeetingsDTO.class) ;
    }
}
