package com.ht.penitancier.web;

import com.ht.penitancier.dtos.PrisonDTO;
import com.ht.penitancier.services.serviceImpl.PrisonServiceImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/prisons/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrisonRestController {

    PrisonServiceImpl prisonService;

    @PostAuthorize("hasAnyAuthority('JUGE', 'ADMINISTRATEUR')")
    @PostMapping(path = "", name = "add new prison", consumes = "application/json")
    public ResponseEntity<PrisonDTO>  add(@Valid @RequestBody(required = true) PrisonDTO request) {
        return new ResponseEntity<>( this.prisonService.add( request), HttpStatus.CREATED );
    }

    @PostAuthorize("hasAnyAuthority('JUGE', 'ADMINISTRATEUR')")
    @RequestMapping( method = RequestMethod.PATCH, path = "/{id}", name = "update prison")
    public ResponseEntity<PrisonDTO> update(@Valid @RequestBody(required = true) PrisonDTO request,
                                            @PathVariable(required = true, name = "id") Long id) {
        return new ResponseEntity<>( this.prisonService.update(request, id), HttpStatus.ACCEPTED );
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR')")
    @PatchMapping(name = "changeStatus", path = "change-status/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeStatus(@PathVariable(name = "id", required = true) Long id) {
      this.prisonService.changeStatus(id);
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR', 'JUGE')")
    @GetMapping(path = "")
    public ResponseEntity<List<PrisonDTO>> prisons() {
        return new ResponseEntity<>( this.prisonService.prisons(), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR', 'JUGE', 'POLICIER_APENAH')")
    @GetMapping(path = "count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<>(this.prisonService.count(), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR', 'JUGE')")
    @GetMapping(path = "{id}", name = "find prison by id")
    public ResponseEntity<PrisonDTO> findById(@PathVariable(name = "id") Long id) {
        return  new ResponseEntity<>( this.prisonService.findById(id), HttpStatus.OK) ;
    }
}
