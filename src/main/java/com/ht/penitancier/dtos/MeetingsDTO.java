package com.ht.penitancier.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
@Builder
public class MeetingsDTO implements Serializable {

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    Long id;

    String raison ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    Date startMeeting ;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/mm/dd HH:mm:ss")
    Date endMeeting ;


    @NotBlank(message = "visitor_name must not be blank")
    @Size(max = 100, min = 2, message = "alias size error")
    @NotNull(message = "visitor_name must be not null")
    String visitor_name ;


    @NotBlank(message = "visitor_cin must not be blank")
    @Size(max = 25, message = "alias size error")
    @NotNull(message = "visitor_cin must be not null")
    String visitor_cin ;


    @NotBlank(message = "visitor_phone must not be blank")
    @Size(max = 100, min = 2, message = "alias size error")
    @NotNull(message = "visitor_phone must be not null")
    String visitor_phone ;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    DetenuDTO detenu ;
}
