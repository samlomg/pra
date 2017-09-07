package com.dglbc.untils;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class TimeUntil {



    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @param format
     * @return
     */
    public String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str   字符串日期
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public String date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return String.valueOf(sdf.parse(date_str).getTime() / 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 取得当前时间戳（精确到秒）
     *
     * @return
     */
    public String timeStampChage(long timesource) {
//        long time = System.currentTimeMillis();  
        String t = String.valueOf(timesource / 1000);
        return t;
    }

    public String obtainCurrentTime(SimpleDateFormat df) {
//    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        return df.format(new Date());
    }

    public String AddDays(String dateString, int addDays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        try {
            cal.setTime(sdf.parse(dateString));
            //加天
            cal.add(Calendar.DATE, addDays);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //减天
//        cal.add(Calendar.DATE, -100);
//        System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()));
        return (new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime());
    }

    public String ObtainLast2Day(int days) {
        // 获取Calendar
        Calendar calendar = Calendar.getInstance();
        // 设置时间,当前时间不用设置
        // calendar.setTime(new Date());
        // 设置日期为本月最大日期
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE) - days);

        // 打印
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//    	System.out.println(format.format(calendar.getTime())); 
        return format.format(calendar.getTime());
    }

    public String AddDaysWithVerificationCurrent(String dateString, int addDays, int defaultdays) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Calendar cal1 = Calendar.getInstance();
        if (dateString == null) {
            System.out.println("==============checkoutDate null=======");
            cal.add(Calendar.DATE, defaultdays);
        } else {

            try {
                cal1.setTime(sdf.parse(dateString));
                cal1.add(Calendar.DATE, addDays);
                if (cal1.getTime().compareTo(new Date()) > 0) {
                    cal.setTime(sdf.parse(dateString));
                    cal.add(Calendar.DATE, addDays);
                } else {
                    cal.add(Calendar.DATE, defaultdays);

                }
            } catch (ParseException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                cal.add(Calendar.DATE, defaultdays);
            }
        }
        //减天
//        cal.add(Calendar.DATE, -100);
//        System.out.println((new SimpleDateFormat("yyyy-MM-dd")).format(cal.getTime()));
        return sdf.format(cal.getTime());
    }

    public boolean compareWithCurrentTime(String dateString) {
        Date date = new Date();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if (date.getTime() > format.parse(dateString).getTime()) {
                return false;
            } else {
                return true;
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static Date stringToDate(String sourceDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(sourceDateString);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            date = new Date();
        }
        return date;
    }

    /**
     * @param sourceDate
     * @param formatString Maybe{yyyy-MM-dd HH:mm:ss}
     * @return String
     */
    public static String dateToString(Date sourceDate, String formatString) {
        SimpleDateFormat df = new SimpleDateFormat(formatString);
        return df.format(sourceDate);
    }


    public static String timeOperation(int year, int mon, int day){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        if (day !=0){
            cal.add(Calendar.DAY_OF_MONTH, day);//天
        }
        if (mon !=0){
            cal.add(Calendar.MONTH, mon);//月
        }
        if (year !=0){
            cal.add(Calendar.YEAR, year);//年
        }
        //Calendar.HOUR_OF_DAY
        //Calendar.MINUTE
        //Calendar.SECOND
        return sdf.format(cal.getTime());
    }

    public static Date timeOperation(Date sourceDate,int year, int mon, int day,int modeType){
        Calendar cal = Calendar.getInstance();
        cal.setTime(sourceDate);
        if (day !=0){
            cal.add(Calendar.DAY_OF_MONTH, day);//天
        }
        if (mon !=0){
            cal.add(Calendar.MONTH, mon);//月
        }
        if (year !=0){
            cal.add(Calendar.YEAR, year);//年
        }
        if (modeType == 1){
            //返回00:00:00
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }else if (modeType ==2){
            //返回23：59：59
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
        }else {

            //返回现在的的时分秒
        }
        //Calendar.HOUR_OF_DAY
        //Calendar.MINUTE
        //Calendar.SECOND
        return cal.getTime();
    }

    public static String timeOperation(int year,int mon,int day,String dataformat){
        SimpleDateFormat sdf = new SimpleDateFormat(dataformat);
        Calendar cal = Calendar.getInstance();
        if (day !=0){
            cal.add(Calendar.DAY_OF_MONTH, day);//天
        }
        if (mon !=0){
            cal.add(Calendar.MONTH, mon);//月
        }
        if (year !=0){
            cal.add(Calendar.YEAR, year);//年
        }
        return sdf.format(cal.getTime());
    }

    public static String timeOperation(String dataformat){
        SimpleDateFormat sdf = new SimpleDateFormat(dataformat);
        return sdf.format(new Date());
    }

    public static Date timeOperationDate(int year, int mon, int day){
        Calendar cal = Calendar.getInstance();
        if (day !=0){
            cal.add(Calendar.DAY_OF_MONTH, day);//天
        }
        if (mon !=0){
            cal.add(Calendar.MONTH, mon);//月
        }
        if (year !=0){
            cal.add(Calendar.YEAR, year);//年
        }
        //Calendar.HOUR_OF_DAY
        //Calendar.MINUTE
        //Calendar.SECOND
        return cal.getTime();
    }

    public static Date timeOperationDate(int year, int mon, int day,boolean is0){
        Calendar cal = Calendar.getInstance();
        if (day !=0){
            cal.add(Calendar.DAY_OF_MONTH, day);//天
        }
        if (mon !=0){
            cal.add(Calendar.MONTH, mon);//月
        }
        if (year !=0){
            cal.add(Calendar.YEAR, year);//年
        }
        if (is0){
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
        }
        return cal.getTime();
    }

    public static Date stringToDate(String dateString,String dataformat) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(dataformat);
        return sdf.parse(dateString);
    }
}
