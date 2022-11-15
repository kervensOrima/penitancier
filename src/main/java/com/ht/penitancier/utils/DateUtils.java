package com.ht.penitancier.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /*
     * get a yea of a date
     * */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR) ;
    }

    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) ;
    }

    /*
    * get age from a date
    * */
    public static int getAge(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        // call the current year calculate the age of the person
        return getYear(new Date()) - calendar.get(Calendar.YEAR) ;
    }


    /*
    *  extract day from a date
    * */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);
        return  calendar.get(Calendar.DAY_OF_MONTH) ;
    }


    /*
    * get time from a specific date
    */
    public static boolean compareTime(Date date) {
        Calendar calendar = Calendar.getInstance() ;
        calendar.setTime(date);

        System.out.println( "Actual month test : " + getMonth(new Date()) + " Month request : " + getMonth(date)) ;
        System.out.println( "Actual year test : " + getYear(new Date()) + " year request : " + getYear(date)) ;
        System.out.println( "Actual day test : " + getDay(new Date()) + " day request : " + getDay(date)) ;
        /*
        * compare month and year and day
        * */
        return  (getMonth(new Date()) == getMonth(date))
                && ( getYear(date) == getYear(new Date()))
                && (getDay(new Date()) == getDay(date));
    }
}
