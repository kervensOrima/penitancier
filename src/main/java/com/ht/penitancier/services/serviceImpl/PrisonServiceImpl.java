package com.ht.penitancier.services.serviceImpl;

import com.ht.penitancier.dtos.PrisonDTO;
import com.ht.penitancier.models.Prison;
import com.ht.penitancier.repositories.PrisonRepository;
import com.ht.penitancier.services.IPrison;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import com.ht.penitancier.utils.enums.PrisonEtatType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
@AllArgsConstructor
public class PrisonServiceImpl implements IPrison {

    ModelMapper modelMapper ;

    PrisonRepository prisonRepository;

    @Override
    public PrisonDTO add(PrisonDTO request) {
        log.info("add new prison");
        Prison prison = this.modelMapper.map(request, Prison.class) ;
        prison.setCreated(new Date());
        prison.setStatus(PrisonEtatType.IN_USE);
        return this.modelMapper.map(this.prisonRepository.save(prison), PrisonDTO.class);
    }

    @Override
    public PrisonDTO update(PrisonDTO request, Long id) {
        log.info("update info about a prison");
        // check if this prison exist in the database
        //  add the old date and update the object
        PrisonDTO dto = this.findById(id);
        request.setStatus(dto.getStatus());
        request.setCreated(dto.getCreated());
        return this.modelMapper
                .map( this.prisonRepository.save(this.modelMapper.map(request, Prison.class)) ,
                        PrisonDTO.class);
    }

    @Override
    public PrisonDTO findById(Long id) {
      return this.modelMapper.map(
              this.prisonRepository.findById(id)
                      .orElseThrow(() -> new ObjectNotFoundException("Prison with id " + id + " is not available!!" ))
              , PrisonDTO.class) ;
    }

    @Override
    public void changeStatus(Long id) {
        log.info("changeStatus");
        if( this.findById(id).getStatus().equals(PrisonEtatType.CLOSE)) {
            this.prisonRepository.changeStatus(PrisonEtatType.IN_USE, id);
        } else {
            this.prisonRepository.changeStatus(PrisonEtatType.CLOSE, id);
        }
    }

    @Override
    public List<PrisonDTO> prisons() {
        log.info("List of prison in the country!!");
        return this.prisonRepository.findAll()
                .stream()
                .map( p -> this.modelMapper.map(p, PrisonDTO.class))
                .collect(Collectors.toList()) ;
    }

    @Override
    public Long count() {
        return this.prisonRepository.count();
    }
}
