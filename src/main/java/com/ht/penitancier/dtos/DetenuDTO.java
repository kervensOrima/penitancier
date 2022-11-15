package com.ht.penitancier.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.penitancier.utils.enums.StatusDetenuType;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
public class DetenuDTO extends PersonDTO implements Serializable {

    @NotBlank(message = "alias must not be blank")
    @Size(max = 100, min = 2, message = "alias size error")
    @NotNull(message = "alias be not null")
    String alias ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Date dateOfImprisonment ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    Date liberation_Date ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    Date date_Judgment ;

    @NotNull(message = "status must be not null")
    StatusDetenuType status ;

    @NotBlank(message = "imageUrl must not be blank")
    @NotNull(message = "imageUrl must be not null", groups = {})
    String imageUrl ;


    PrisonDTO prison ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String code ;


    public DetenuDTO(Long id, String firstName, String lastname, String genre, String phoneNumber, Date birthDate, String cin,
                     String code, String alias, Date dateOfImprisonment, Date liberation_Date, Date date_Judgment, StatusDetenuType status,
                     String imageUrl,  PrisonDTO prison) {
        super(id, firstName, lastname, genre, phoneNumber, birthDate, cin);
        this.code = code;
        this.alias = alias;
        this.dateOfImprisonment = dateOfImprisonment;
        this.liberation_Date = liberation_Date;
        this.date_Judgment = date_Judgment;
        this.status = status;
        this.imageUrl = imageUrl;
        this.prison = prison;
    }


}
