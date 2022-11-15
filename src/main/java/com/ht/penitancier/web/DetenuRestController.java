package com.ht.penitancier.web;

import com.ht.penitancier.dtos.DetenuDTO;
import com.ht.penitancier.services.serviceImpl.DetenuServiceImpl;
import com.ht.penitancier.utils.enums.StatusDetenuType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/detenus/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DetenuRestController {

    DetenuServiceImpl detenuService;

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR')")
    @PostMapping(path = "", consumes = "application/json")
    public ResponseEntity<DetenuDTO> save(@Valid @RequestBody(required = true) DetenuDTO request) {
        return new ResponseEntity<>( this.detenuService.save(request), HttpStatus.CREATED);
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR')")
    @GetMapping(path = "{detenu_id}")
    public ResponseEntity<DetenuDTO> get(@PathVariable(required = true, name = "detenu_id") Long id) {
        return new ResponseEntity<>( this.detenuService.get(id), HttpStatus.OK );
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR')")
    @PatchMapping(path = "{id_detenu}", consumes = "application/json")
    public ResponseEntity<DetenuDTO> update(@Valid @RequestBody(required = true)  DetenuDTO request, @PathVariable(
            required = true,
            name = "id_detenu") Long id) {
        return new ResponseEntity<>( this.detenuService.update(request, id), HttpStatus.ACCEPTED);
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'JUGE')")
    @GetMapping(path = "")
    public ResponseEntity<List<DetenuDTO>> all() {
        return new ResponseEntity<>(this.detenuService.all(), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'JUGE')")
    @GetMapping(path = "{prison}/")
    public ResponseEntity<Page<DetenuDTO>> all(@PathVariable(name = "prison") Long prisonId,
                                              @RequestParam(name = "page") int page,
                                               @RequestParam(name = "page") int size) {
        return new ResponseEntity<>( this.detenuService.all(prisonId, page, size), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'JUGE')")
    @GetMapping(path = "{prison}/{status}/")
    public ResponseEntity<Page<DetenuDTO>> all(@PathVariable(name = "status") StatusDetenuType status,
                                              @PathVariable(name = "prison") Long pisonId,
                                               @RequestParam(name = "page") int page,
                                               @RequestParam(name = "size") int size) {
        return new ResponseEntity<>(this.detenuService.all(status, pisonId, page, size), HttpStatus.OK );
    }

    @PostAuthorize("hasAnyAuthority('JUGE')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PatchMapping(path = "{detenu}/{status}")
    public void changeStatusDetenu(@PathVariable(name = "detenu") Long idDetenu,
                                   @PathVariable(name = "status") StatusDetenuType status) {
        this.detenuService.changeStatusDetenu(idDetenu, status);
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'JUGE')")
    @GetMapping(path = "total/")
    public ResponseEntity<Long> total() {
        return new ResponseEntity<>(this.detenuService.total(), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('JUGE', 'ADMINISTRATEUR')")
    @GetMapping(path = "total/{prison}/")
    public ResponseEntity<Long> totalByPrison(@PathVariable(name = "prison") Long idPrison) {
        return new ResponseEntity<>( this.detenuService.totalByPrison(idPrison), HttpStatus.OK);
    }
}
