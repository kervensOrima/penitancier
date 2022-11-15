package com.ht.penitancier.services.serviceImpl;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    private static final DateFormat df =  new SimpleDateFormat("yyyy/MM/dd HH:mm:ss sss") ;

    //@Scheduled(initialDelay = 10 * 1000, fixedDelay = 10 * 1000)
    public void writeCurrentTime(){
        Date date  = new Date() ;
        String now = df.format(date) ;
        System.out.println("L'heure actuel du système est : " + now );
    }

    /* @Scheduled(cron = "0 20 1 * * MON-THU") */
    public void doSomething() {
        System.out.println("Do some thing");
    }
}
