package com.ht.penitancier.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



@org.springframework.context.annotation.Configuration
public class Configuration {

    public static final String SECRET_KEY = "secret" ;

    public static final  String KEY = "Bearer " ;

    public static final int KEY_COUNT = KEY.length() ;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper() ;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }


}
