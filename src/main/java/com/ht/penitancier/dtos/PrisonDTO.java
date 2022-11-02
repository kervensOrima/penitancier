package com.ht.penitancier.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.penitancier.utils.enums.PrisonEtatType;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PrisonDTO  implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id ;

    @NotBlank(message = "name is required")
            @NotEmpty(message = "name is required")
            @Size(max = 100, min = 2)
    String name ;

    String department ;

    String city ;

    PrisonEtatType status ;

    Integer roomNumbers;

    Integer guardNumbers ;

    String chef ;

    String chef_phone ;

}
