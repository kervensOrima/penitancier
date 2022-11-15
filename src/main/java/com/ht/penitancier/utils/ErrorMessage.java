package com.ht.penitancier.utils;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorMessage {

    String message ;
    Integer code ;
    Date timestamp ;
    String objectName;

}
