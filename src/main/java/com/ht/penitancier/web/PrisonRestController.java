package com.ht.penitancier.web;

import com.ht.penitancier.dtos.PrisonDTO;
import com.ht.penitancier.services.IPrison;
import com.ht.penitancier.services.serviceImpl.PrisonServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/prisons/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrisonRestController {

    PrisonServiceImpl prisonService;

    public ResponseEntity<PrisonDTO>  add(@Valid PrisonDTO request) {
        return null;
    }

    public ResponseEntity<PrisonDTO> update(@Valid PrisonDTO request, Long id) {
        return null;
    }

    public void changeStatus(Long id) {

    }

    public ResponseEntity<List<PrisonDTO>> prisons() {
        return null;
    }

    public ResponseEntity<Long> count() {
        return null;
    }
}
