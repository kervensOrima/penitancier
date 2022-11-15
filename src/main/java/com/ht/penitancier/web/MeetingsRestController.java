package com.ht.penitancier.web;

import com.ht.penitancier.dtos.MeetingsDTO;
import com.ht.penitancier.services.serviceImpl.MeetingsServiceImpl;
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
@RequestMapping(path = "/meetings/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MeetingsRestController  {

    MeetingsServiceImpl meetingsService ;

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'ADMINISTRATEUR')")
    @PostMapping(path = "save/{detenu}/", consumes = "application/json", produces = "application/json")
    public ResponseEntity<MeetingsDTO>  save(@Valid @RequestBody MeetingsDTO request, @PathVariable("detenu") Long detenu) {
        return new ResponseEntity<>( this.meetingsService.save(request, detenu), HttpStatus.CREATED );
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'ADMINISTRATEUR')")
    @PatchMapping(path = "update/{detenu}/")
    public ResponseEntity<MeetingsDTO> update(@Valid @RequestBody MeetingsDTO request,@PathVariable("detenu") Long detenu) {
        return new ResponseEntity<>( this.meetingsService.update(request, detenu), HttpStatus.CREATED );
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'ADMINISTRATEUR')")
    @GetMapping(path = "get/{detenu}/")
    public ResponseEntity<Page<MeetingsDTO>> meetings(@RequestParam(name = "page", defaultValue = "0") int page,
                                                      @RequestParam(name = "size", defaultValue = "100") int size,
                                                      @PathVariable("detenu")  Long detenuId) {
        return new ResponseEntity<>(this.meetingsService.meetings(page, size, detenuId), HttpStatus.OK );
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR')")
    @GetMapping(path = "get/")
    public ResponseEntity<List<MeetingsDTO>> meetings() {
        return new ResponseEntity<>(this.meetingsService.meetings(), HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR')")
    @GetMapping(path = "get/{detenu}/{year}/")
    public ResponseEntity<Long> meetings(  @PathVariable("detenu")Long detenuId,  @PathVariable("year") Long year) {
        return new ResponseEntity<>(this.meetingsService.meetings(detenuId, year), HttpStatus.OK );
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR')")
    @GetMapping(path = "get/count/{detenuId}/")
    public ResponseEntity<Long> meetings(@PathVariable("detenuId") Long detenuId) {
        return new ResponseEntity<>(this.meetingsService.meetings(detenuId), HttpStatus.OK );
    }

    @PostAuthorize("hasAnyAuthority('DIRECTEUR', 'ADMINISTRATEUR', 'POLICIER_APENAH')")
    @GetMapping(path = "get-one/{meeting}/")
    public ResponseEntity<MeetingsDTO> get( @PathVariable("meeting") Long meetingsId) {
        return new ResponseEntity<>(this.meetingsService.get(meetingsId), HttpStatus.OK );
    }
}
