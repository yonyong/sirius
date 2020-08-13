package top.yonyong.sirius.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author yonyong
 */
public class DateUtil {
    public static String getDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }

    /**
     * 月份减一
     */
    public static String monthLostOne(String datetime){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, -1);
        date = cl.getTime();
        return sdf.format(date);
    }

    /**
     * 日期减去一个月
     */
    public static String subMonth(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(dt);
        rightNow.add(Calendar.MONTH, -1);
        Date dt1 = rightNow.getTime();
        String reStr = sdf.format(dt1);
        return reStr;
    }

    /**
     * 获取月份的最后一天
     */
    private static String getMonthMaxDay(String sDate) {
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Date date = null;
        try {
            date = sdf_full.parse(sDate + "-01");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cal.setTime(date);
        int last = cal.getActualMaximum(Calendar.DATE);
        return String.valueOf(last);
    }

    /**
     * 判断是否是月末
     */
    public static boolean isMonthEnd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DATE) == cal.getActualMaximum(Calendar.DAY_OF_MONTH))
            return true;
        else
            return false;
    }

    /**
     * 日期减一天、加一天
     */
    public static String checkOption(String option, String _date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        cl.setTime(date);
        if ("pre".equals(option)) {
            // 时间减一天
            cl.add(Calendar.DAY_OF_MONTH, -1);

        } else if ("next".equals(option)) {
            // 时间加一天
            cl.add(Calendar.DAY_OF_YEAR, 1);
        } else {
            // do nothing
        }
        date = cl.getTime();
        return sdf.format(date);
    }

    /***
     * 判断日期是否为当前月， 是当前月返回当月最小日期和当月目前最大日期以及传入日期上月的最大日和最小日
     * 不是当前月返回传入月份的最大日和最小日以及传入日期上月的最大日和最小日
     */
    public static String[] getNow_Pre_Date(String date) throws ParseException {

        String[] str_date = new String[4];
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf_full = new SimpleDateFormat("yyyy-MM-dd");
        String stMonth = sdf.format(now);
        // 开始日期
        String stdate = "";
        // 结束日期
        String endate = "";
        // 上月开始日期
        String preDate_start = "";
        // 上月结束日期
        String preDate_end = "";

        // 当前月
        if (date.equals(stMonth)) {
            stdate = stMonth + "-01";
            endate = sdf_full.format(now);
            preDate_start = subMonth(stdate);
            preDate_end = subMonth(endate);
        } else {
            String monthMaxDay = getMonthMaxDay(date);
            stdate = date + "-01";
            endate = date + "-" + monthMaxDay;
            preDate_start = subMonth(stdate);
            preDate_end = subMonth(endate);
        }
        str_date[0] = stdate;
        str_date[1] = endate;
        str_date[2] = preDate_start;
        str_date[3] = preDate_end;

        return str_date;
    }

}
