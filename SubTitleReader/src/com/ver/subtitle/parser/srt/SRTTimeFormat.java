package com.ver.subtitle.parser.srt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * This class contains utility methods for SRT time format related stuff.
 * 
 * @author fredy
 */
public class SRTTimeFormat {
    public static final String TIME_DELIMITER = " --> ";
    public static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String HOUR_FORMAT = "HH";
    public static final String MINUTE_FORMAT = "mm";
    public static final String SECOND_FORMAT = "ss";
    public static final String MILLISECOND_FORMAT = "SSS";
    private static final SimpleDateFormat sdf = new SimpleDateFormat(TIME_FORMAT);
    
    public enum Type {
        HOUR,
        MINUTE,
        SECOND,
        MILLISECOND
    }
    
    public static class SRTTime {
        public final int hour;
        public final int minute;
        public final int second;
        public final int millisecond;
        
        
        public SRTTime(int hour, int minute, int second, int millisecond) {
            this.hour = hour;
            this.minute = minute;
            this.second = second;
            this.millisecond = millisecond;
        }
    }
    
    private SRTTimeFormat() {
    }
    
    /**
     * Formats the date into SRT time format.
     * @param date the date
     * @return the SRT time format
     */
    public static String format(Date date) {
        return sdf.format(date);
    }
    
    /**
     * Parses the SRT time format into date.
     * @param srtTime the SRT time format
     * @return the date
     * @throws ParseException
     */
    public static Date parse(String srtTime) throws ParseException {
    	sdf.setTimeZone(TimeZone.getTimeZone("GMT0"));
    	Date date= sdf.parse(srtTime);
        return date;
    }
    
    /**
     * Converts Date to SRTTime.
     * 
     * @param date the date
     * @return the SRTTime
     */
    public static SRTTime toSRTTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new SRTTime(
            cal.get(Calendar.HOUR),
            cal.get(Calendar.MINUTE),
            cal.get(Calendar.SECOND),
            cal.get(Calendar.MILLISECOND));
    }
    
    /**
     * Converts SRTTime to Date.
     * 
     * @param srtTime the SRTTime
     * @return the Date
     * @throws ParseException
     */
    public static Date fromSRTTime(SRTTime srtTime) throws ParseException {
        StringBuilder timeStr = new StringBuilder();
        if (srtTime.hour < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.hour));
        timeStr.append(":");
        if (srtTime.minute < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.minute));
        timeStr.append(":");
        if (srtTime.second < 10) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.second));
        timeStr.append(",");
        if (srtTime.second < 10) {
            timeStr.append("00");
        } else if (srtTime.second < 100) {
            timeStr.append("0");
        }
        timeStr.append(Integer.toString(srtTime.millisecond));
        
        return SRTTimeFormat.parse(timeStr.toString());
    }
}
