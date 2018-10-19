package alphabet.adviserconn.InvitationFromDarkNet.utils;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import alphabet.adviserconn.config.SystemParams;


/**
 * 类描述：
 * 创建人：Administrator
 * 创建时间：2018/7/27 0027
 */
public class TimeUtils {


    /**
     * Get the date which player open the application, return the next day of this date.
     *
     * @param time
     * @param n
     * @return
     */
    public static String addDay(Long time, int n) {
//        SystemParams systemParams = SystemParams.getInstance();
//        long time = systemParams.getLong("time");
        Calendar calendar = Calendar.getInstance();
        Date date = new Date(time);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, n);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(calendar.getTime());
        return format.substring(4, 6) + "月" + format.substring(6, 8) + "日";
    }

    /**
     * @description Compare start time and current time to judge whether it passed one day or not
     * @author Zhawenting(Ada)
     * @date 2018/9/23 11:43
     */
    public static boolean compareDay(long startTime,int gap) {
        //Get start time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(startTime);
        int startYear = Integer.parseInt(format.substring(0, 4));
        int startMonth = Integer.parseInt(format.substring(4, 6));
        int startDay = Integer.parseInt(format.substring(6, 8));
        //Get current time
        long currentTime = System.currentTimeMillis();
        format = sdf.format(currentTime);
        int currentYear = Integer.parseInt(format.substring(0, 4));
        int currentMonth = Integer.parseInt(format.substring(4, 6));
        int currentDay = Integer.parseInt(format.substring(6, 8));

        if (currentYear > startYear || currentMonth > startMonth || currentDay >= startDay+gap)
            return true;
        else
            return false;
    }



    /**
     * @description Compare start time and current time to judge whether it passed some minutes or not
     * @author Zhawenting(Ada)
     * @date 2018/9/23 11:43
     */
    public static boolean compareMinutes(long startTime,int gap) {
        //Get start time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(startTime);
        int startYear = Integer.parseInt(format.substring(0, 4));
        int startMonth = Integer.parseInt(format.substring(4, 6));
        int startDay = Integer.parseInt(format.substring(6, 8));
        int startHour = Integer.parseInt(format.substring(8, 10));
        int startMinute = Integer.parseInt(format.substring(10, 12));
        //Get current time
        long currentTime = System.currentTimeMillis();
        format = sdf.format(currentTime);
        int currentYear = Integer.parseInt(format.substring(0, 4));
        int currentMonth = Integer.parseInt(format.substring(4, 6));
        int currentDay = Integer.parseInt(format.substring(6, 8));
        int currentHour = Integer.parseInt(format.substring(8, 10));
        int currentMinute = Integer.parseInt(format.substring(10, 12));

        if (currentYear > startYear || currentMonth > startMonth || currentDay > startDay||startHour>currentHour||startMinute>=currentMinute+gap)
            return true;
        else
            return false;
    }

    /**
     * @description Compare start time and current time to judge whether it passed some minutes or not
     * @author Zhawenting(Ada)
     * @date 2018/9/23 11:43
     */
    public static boolean compareHours(long startTime,int gap) {
        //Get start time
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(startTime);
        int startYear = Integer.parseInt(format.substring(0, 4));
        int startMonth = Integer.parseInt(format.substring(4, 6));
        int startDay = Integer.parseInt(format.substring(6, 8));
        int startHour = Integer.parseInt(format.substring(8, 10));
        //Get current time
        long currentTime = System.currentTimeMillis();
        format = sdf.format(currentTime);
        int currentYear = Integer.parseInt(format.substring(0, 4));
        int currentMonth = Integer.parseInt(format.substring(4, 6));
        int currentDay = Integer.parseInt(format.substring(6, 8));
        int currentHour = Integer.parseInt(format.substring(8, 10));

        if (currentYear > startYear || currentMonth > startMonth || currentDay > startDay||startHour>=currentHour+gap)
            return true;
        else
            return false;
    }


}
