package com.ht.penitancier.services.serviceImpl;

import com.ht.penitancier.dtos.PrisonDTO;
import com.ht.penitancier.models.Prison;
import com.ht.penitancier.repositories.PrisonRepository;
import com.ht.penitancier.services.IPrison;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Prison prison ;
        return null;
    }

    @Override
    public PrisonDTO update(PrisonDTO request, Long id) {
        return null;
    }

    @Override
    public void changeStatus(Long id) {

    }

    @Override
    public List<PrisonDTO> prisons() {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
