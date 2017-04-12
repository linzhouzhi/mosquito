package com.lzz.util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by lzz on 17/4/12.
 */
public class CommonUtil {
    public static int getDay(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date() );
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }
    public static int getHour(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date() );
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour;
    }
    public static int getMinute(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Date() );
        int minute = calendar.get(Calendar.MINUTE);
        return minute;
    }

}
