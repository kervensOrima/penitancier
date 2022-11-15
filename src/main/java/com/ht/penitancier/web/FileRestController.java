package com.ht.penitancier.web;

import com.ht.penitancier.services.exceptions.ObjectNotFoundException;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@RestController
@RequestMapping(path = "/assets/")
@AllArgsConstructor
@CrossOrigin(value = "*")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class FileRestController {

    public static final String DIRECTORY = System.getProperty("user.dir") +
            File.separator + "src" +
            File.separator + "main" +
            File.separator + "resources" +
            File.separator + "assets" +
            File.separator + "images";

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR')")
    @PostMapping(path = "upload/", produces = "multipart/form-data" , consumes = "multipart/form-data")
    public ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file) throws Exception {
        String fileName = "" ;
            if (file != null)
                if (!file.isEmpty()) {
                    try {
                        fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
                        Path path = Paths.get(DIRECTORY, fileName).toAbsolutePath().normalize();
                        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                    } catch (Exception exception) {
                        log.error("file error message {}", exception.getMessage() );
                    }
                }
        return new ResponseEntity<>( fileName , HttpStatus.OK);
    }

    @PostAuthorize("hasAnyAuthority('POLICIER_APENAH', 'DIRECTEUR', 'JUGE', 'ADMINISTRATEUR')")
    @GetMapping(path = "download/{fileName}")
    public ResponseEntity<Resource> download(@PathVariable(name = "fileName") String fileName) throws IOException {
        Path path = Paths.get( DIRECTORY ).toAbsolutePath().normalize().resolve(fileName) ;
        if (! Files.exists(path)) {
          throw  new ObjectNotFoundException( fileName + " not found try with another filename!")  ;
        }
        Resource resource = new UrlResource(path.toUri()) ;
        HttpHeaders httpHeaders = new HttpHeaders() ;
        httpHeaders.add("file-name", fileName );
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment;File-name=" + resource.getFilename() );
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(path)))
                .headers(httpHeaders)
                .body(resource);
    }


    public static void main(String[] args) {
        File f = new File(DIRECTORY) ;
        System.out.println(f.exists());
        System.out.println(DIRECTORY);
    }
}
