package ca.itsd.guli.util;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * this is a utility class that handles time
 */
public class TimeUtil {

    /**
     * get the current time in yyyy-MM-dd HH:mm:ss format
     * @return the current time
     */
    public static String getCurrentTime()
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now=new Date();
        String currentTime=sdf.format(now);
        return currentTime;
    }
}
