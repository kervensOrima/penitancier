package com.ht.penitancier.services.serviceImpl;

import com.ht.penitancier.dtos.DetenuDTO;
import com.ht.penitancier.models.Detenu;
import com.ht.penitancier.repositories.PersonRepository;
import com.ht.penitancier.services.IDetenu;
import com.ht.penitancier.services.exceptions.AgeInvalidException;
import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import com.ht.penitancier.services.exceptions.YearInvalidException;
import com.ht.penitancier.utils.DateUtils;
import com.ht.penitancier.utils.ObjectMapper;
import com.ht.penitancier.utils.enums.StatusDetenuType;
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

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@Transactional
@AllArgsConstructor
public class DetenuServiceImpl implements IDetenu {

    ModelMapper modelMapper;

    PersonRepository detenuRepository;

    PrisonServiceImpl prisonService;

    private Pageable page(int page, int size) {
        return PageRequest
                .of(page, size, Sort.by("liberation_Date").ascending());
    }

    /*
    private Person findByCin(String cin) {
        return this.detenuRepository
                .findByCin(cin)
                .orElseThrow(() ->
                        new ObjectNotFoundException("Detenu with cin " + cin + " is not yet present on the system"));
    }
     */

    @Override
    public DetenuDTO save(DetenuDTO request) {
        log.info("save a detenu ==== > {}", request.toString());
        //Check that the chosen prison exists on the system
        this.prisonService.findById(request.getPrison().getId());
        // check the date of birth
        int age = DateUtils.getAge(request.getBirthDate());
        if (age < 18) {
            throw new AgeInvalidException("Age invalid person is not major!! the age is " + age + " ans.");
        }
        if (DateUtils.getYear(request.getDate_Judgment()) < DateUtils.getYear(new Date())) {
            throw new YearInvalidException("the date of the judgement is invalid");
        }
        // convert detenu dto to detenu
        Detenu detenu = ObjectMapper.getDetenu(request);
        // we have to save the new detenu
        detenu.setCode(UUID.randomUUID().toString());
        detenu.setDateOfImprisonment(new Date());
        // convert detenu to detenu dto and return the object save
        return ObjectMapper.getDetenuDTO(this.detenuRepository.save(detenu));
    }

    @Override
    public DetenuDTO get(Long id) {
        log.info("Find detenu by id");
        return this.modelMapper
                .map(this.detenuRepository
                                .findById(id)
                        .orElseThrow(() -> new
                                ObjectNotFoundException("Detenu with " + id + " is not yet present on the system"))
                , DetenuDTO.class);
    }

    @Override
    public DetenuDTO update(DetenuDTO request, Long id) {
        log.info("update detenu information prison , {}", request.getPrison());
        // check if detenu already exist:
        DetenuDTO detenuDTO = this.get(id);
        // cat detenu dto object to detenu
        Detenu detenu = ObjectMapper.getDetenu(request);
        // pass the id to modify detenu info
        detenu.setId(id);
        detenu.setCode(detenu.getCode());
        detenu.setDateOfImprisonment(detenuDTO.getDateOfImprisonment());
        // save detenu and return and convert detenu to detenu dto
        return ObjectMapper.getDetenuDTO(this.detenuRepository.save(detenu));
    }

    @Override
    public List<DetenuDTO> all() {
        log.info("find all detenu");
        return this.detenuRepository
                .findAll()
                .stream()
                .map(detenu -> this.modelMapper.map(detenu, DetenuDTO.class))
                .collect(Collectors.toList());
    }


    @Override
    public Page<DetenuDTO> all(Long prisonId, int page, int size) {
        log.info("find all by prison");
        return this.detenuRepository
                .all(this.page(page, size), prisonId)
                .map(detenu -> this.modelMapper.map(detenu, DetenuDTO.class));
    }

    @Override
    public Page<DetenuDTO> all(StatusDetenuType status, Long prisonId, int page, int size) {
        log.info("find all by status and prison");
        return this.detenuRepository
                .all(this.page(page, size), status, prisonId)
                .map(detenu -> this.modelMapper.map(detenu, DetenuDTO.class));
    }

    @Override
    public void changeStatusDetenu(Long idDetenu, StatusDetenuType status) {
        this.detenuRepository.changeStatusDetenu(idDetenu, status);
    }

    @Override
    public Long total() {
        return this.detenuRepository.count();
    }

    @Override
    public Long totalByPrison(Long idPrison) {
        return this.detenuRepository.totalByPrison(idPrison);
    }
}
