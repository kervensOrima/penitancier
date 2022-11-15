package com.ht.penitancier.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ht.penitancier.utils.enums.PrisonEtatType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
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

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    Long id ;

    @NotBlank(message = "name must not be blank")
    @Size(max = 100, min = 2, message = "name Size error")
    String name ;

    @NotBlank(message = "department must not be blank")
    @Size(max = 100, min = 2, message = "department Size error")
    String department ;

    @NotBlank(message = "city must not be blank")
    @Size(max = 100, min = 2, message = "city Size error")
    String city ;

    @Min(message = "Room number is invalid", value = 1)
    @Max(message = "Room number is invalid", value = 100_000_000)
    Integer roomNumbers;

    @Min(message = "guard numbers number is invalid", value = 1)
    @Max(message = "guard numbers number is invalid", value = 100_000_000)
    Integer guardNumbers ;

    @NotBlank(message = "chef must not be blank")
    @Size(max = 100, min = 2, message = "chef Size error")
    String chef ;

    @NotBlank(message = "chef phone number must not be blank")
    @Size(max = 20, min = 8, message = "chef_phone Size error")
    String chef_phone ;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    PrisonEtatType status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Date created ;

}
