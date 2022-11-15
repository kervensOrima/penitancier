package com.ht.penitancier.dtos;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@AllArgsConstructor
public abstract class PersonDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id ;

    @NotBlank(message = "first name must not be blank")
    @Size(max = 100, min = 2, message = "first name size error")
    @NotNull(message = "first name must be not null")
    String firstName;

    @NotBlank(message = "lastname  must not be blank")
    @Size(max = 100, min = 2, message = "lastname size error")
    @NotNull(message = "lastname must be not null")
    String lastname;

    @NotBlank(message = "first name must not be blank")
    @Size(max = 10, min = 4, message = "first name size error")
    @NotNull(message = "genre must be not null")
    String genre ;

    @NotBlank(message = "genre must not be blank")
    @Size(max = 15, min = 8, message = "genre size error")
    @NotNull(message = "genre must be not null")
    String phoneNumber ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd")
    @JsonFormat(pattern = "yyyy/mm/dd")
    Date birthDate ;

    @NotBlank(message = "cin must not be blank")
    @Size(max = 100, min = 2, message = "cin size error")
    @NotNull(message = "cin must be not null")
    String cin ;
}
