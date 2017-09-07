package com.dglbc.untils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommonUntil {

    protected Log logger = LogFactory.getLog(getClass());

    public String deleteNull(String data) {
        String resultData;
        if (data == null || data.length() <= 0) {
            resultData = " ";
        } else {
            resultData = data;
        }
        return resultData;
    }

    public String getSubFornt(String data, int numb) {
        return data.substring(0, data.length() - numb);
    }

    /**
     * 判断对象或对象数组中每一个对象是否为空: 对象为null，字符序列长度为0，集合类、Map为empty
     *
     * @param obj
     * @return
     */

    @SuppressWarnings("rawtypes")
    public boolean isNullOrEmpty(Object obj) {
        if (obj == null)
            return true;

        if (obj instanceof CharSequence)
            return ((CharSequence) obj).length() == 0;

        if (obj instanceof Collection)
            return ((Collection) obj).isEmpty();

        if (obj instanceof Map)
            return ((Map) obj).isEmpty();

        if (obj instanceof Object[]) {
            Object[] object = (Object[]) obj;
            if (object.length == 0) {
                return true;
            }
            boolean empty = true;
            for (int i = 0; i < object.length; i++) {
                if (!isNullOrEmpty(object[i])) {
                    empty = false;
                    break;
                }
            }
            return empty;
        }
        return false;
    }

    public String birthFromSN(String sourceString) {
        if (sourceString == null) {
            return null;
        }
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormater2 = new SimpleDateFormat("yyyyMMdd");
        Date targetTimeString = null;
        try {
            targetTimeString = dateFormater2.parse(sourceString.substring(6, 14));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateFormater.format(targetTimeString);
    }


    public String dateExchange(String datesourcestring) {
        if (datesourcestring == null) {
            return null;
        }
        Pattern p = Pattern.compile("([\\-]?[\\d]{10})");
        Matcher m = p.matcher(datesourcestring);
        m.find();
        String targetString = m.group();
//		System.out.println(targetString);
        String targetDate = timeStamp2Date(targetString, "yyyy-MM-dd HH:mm:ss");
//        System.out.println(targetDate);
        return targetDate;
    }

    public String dateExchange3(String datesourcestring) {
        if (datesourcestring == null) {
            return null;
        }
        System.out.println("E3datesourcestring:" + datesourcestring);
        Pattern p = Pattern.compile("([\\-]?[\\d]{10})");
        Matcher m = p.matcher(datesourcestring);
        m.find();
        String targetString = m.group();
        System.out.println(targetString);

        String targetDate = timeStamp2Date(targetString, "yyyy-MM-dd");
//        System.out.println(targetDate);
        return targetDate;

    }

    public String dateExchange4(String datesourcestring) {
        logger.info("datesourcestring值：==="+datesourcestring+"===");
        if (datesourcestring == null | datesourcestring == ""| datesourcestring == " ") {
            return null;
        }
        try {
            System.out.println("E4datesourcestring:" + datesourcestring);
            Pattern p = Pattern.compile("[\\d]{4}-[\\d]{2}-[\\d]{2}");
            Matcher m = p.matcher(datesourcestring);
            m.find();
            String targetString = m.group();
            System.out.println(targetString);

            return targetString;

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }

    }

    public String replaceIllegalCharacter(String reg, String source, String replaceString) {
        if (source == null)
            return source;
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(source);
        return m.replaceAll(replaceString);
    }

    public String replaceNull(String sourceString) {
        String returnString = new String();
        try {

            if (sourceString == null || sourceString.equals("null") || sourceString.length() <= 0) {
                returnString = "S_U00";
            } else if (sourceString.equals("0")) {
                returnString = "S_U01";
            } else if (sourceString.equals("0.0")) {
                returnString = "S_U01";
            } else if (sourceString == "") {
                returnString = "S_U01";
            } else {
                returnString = sourceString.replaceAll("\'", "S_U02");
                returnString = returnString.replaceAll("\"", "S_U03");
                returnString = returnString.replaceAll(":", "S_U04");
                returnString = returnString.replaceAll("[\\t\\n\\r]", " <br> ");
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

//		System.out.println("returnString:"+returnString);
        return returnString;
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
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


    public String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }


    public String deleteEndWordWithSpecial(String sourceString) {
        if (sourceString == null | sourceString.trim().length() == 0 |sourceString == ""){
            return "暂无提供";
        }
        if (sourceString.endsWith("@")) {
            sourceString = sourceString.substring(0, sourceString.length() - 1);
        } else if (sourceString.endsWith("-")) {
            sourceString = sourceString.substring(0, sourceString.length() - 1);
        }
        return sourceString;
    }

    public String nullOrSpaceRepalce(String sourceString) {
        if (sourceString.trim().length() == 0 || sourceString == "" || sourceString.equals("S_U00") || sourceString.equals("S_U01")) {
            return "0";
        } else {
            return sourceString;
        }
    }

    public int nullOrSpaceRepalce(int sourceString) {
        if (sourceString - 1 > 0) {
            return sourceString;
        } else {
            return 0;
        }

    }


    /**
     *网页经过处理的字符，回转
     * Stringback 普通版本
     */
    public String stringBack(String sourceString) {
        String returnString = new String();
        if (sourceString == null || sourceString.trim().length() == 0 || sourceString == "" || sourceString.equals("S_U00") || sourceString.equals("S_U01") ||sourceString.equals("null")) {
            returnString= "待补充";
        }else {
            returnString = sourceString.replaceAll("S_U02","\'");
            returnString = returnString.replaceAll( "S_U03","\"");
            returnString = returnString.replaceAll("S_U04",":");
        }
        return returnString;

    }
    /**
     *网页经过处理的字符，回转
     * Stringback 返回0
     */
    public String stringBack0(String sourceString) {
        String returnString = new String();
        if (sourceString == null || sourceString.trim().length() == 0 || sourceString == "" || sourceString.equals("S_U00") || sourceString.equals("S_U01") ||sourceString.equals("null")) {
            returnString= "0";
        }else {
            returnString = sourceString.replaceAll("S_U02","\'");
            returnString = returnString.replaceAll( "S_U03","\"");
            returnString = returnString.replaceAll("S_U04",":");
        }
        return returnString;

    }


    /**
     * 处理timestamp
     * @param datesourcestring
     * @param formartString
     * @return
     */
    public String stringTimeStampToDateString(String datesourcestring,String formartString) {
        if (datesourcestring == null || datesourcestring.length() <= 0) {
            return null;
        }
        if (datesourcestring =="null"){
            logger.info("被转成NULL字符串了");
            return null;
        }
        logger.info("datesourcestring:"+datesourcestring);
        logger.info("formartString:"+formartString);
        String targetString=datesourcestring.substring(6,datesourcestring.length()-10);
        String targetDate = timeStamp2Date(targetString, formartString);
        logger.info("targetDate:"+targetDate);
        return targetDate;
    }

    public Document loadDocumentFormxml(String filePath){
        Document document=null;
        //Path PatientLocation = LocationPathDiff.resolve(patient+".htm.xml");
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(new File(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return document;

    }
    public Map loadDocumentFormxmlF(String filePath){
        Map<String,Object> returnMap =new HashMap<>();
        String statusCode = "success";
        Document document=null;
        //Path PatientLocation = LocationPathDiff.resolve(patient+".htm.xml");
        SAXReader reader = new SAXReader();
        try {
            document = reader.read(new File(filePath));
        } catch (DocumentException e) {
            e.printStackTrace();
            statusCode = "Read Fail";
        }
        returnMap.put("statusCode",statusCode);
        returnMap.put("returnResult",document);
        return returnMap;

    }

    public String switchMarital(String sourceString){
        String returnCode=null;
        if (isNullOrEmpty(sourceString)){
            returnCode = "其他";
            return returnCode;
        }
        switch (sourceString) {
            case "1":
                sourceString = "未婚";
                break;
            case "2":
                sourceString = "已婚";
                break;
            case "3":
                sourceString = "丧偶";
                break;
            case "4":
                sourceString = "离婚";
                break;
            case "9":
                sourceString = "其他";
                break;
            default:
                sourceString = "其他";
        }
        return returnCode;
    }
    public Map switchInsurance(String sourceString){
        Map<String,String> returnMap= new HashMap<String,String>();

        String hasInsurance = null;
        String insuranceLocal = null;
        String hasBusinessInsurance = "0";
        String DetailOfInsurance = null;
        switch (sourceString) {
            case "1":
                hasInsurance = "1";
                insuranceLocal = "东莞社保";
                hasBusinessInsurance = "0";
                DetailOfInsurance = "无";
                break;
            case "N":
                hasInsurance = "1";
                insuranceLocal = "东莞社保";
                hasBusinessInsurance = "0";
                DetailOfInsurance = "无";
                break;
            case "2":
                hasInsurance = "1";
                insuranceLocal = "东莞社保";
                hasBusinessInsurance = "0";
                DetailOfInsurance = "无";
                break;
            case "3":
                hasInsurance = "1";
                insuranceLocal = "东莞社保";
                hasBusinessInsurance = "0";
                DetailOfInsurance = "无";
                break;
            case "5":
                hasInsurance = "1";
                insuranceLocal = "商业社保";
                hasBusinessInsurance = "1";
                DetailOfInsurance = "有商业社保";
                break;

            default:
                hasInsurance = null;
                insuranceLocal = " ";
                hasBusinessInsurance = "0";
                DetailOfInsurance = "无";
                break;
        }
        returnMap.put("hasInsurance",hasInsurance);
        returnMap.put("insuranceLocal",insuranceLocal);
        returnMap.put("hasBusinessInsurance",hasBusinessInsurance);
        returnMap.put("DetailOfInsurance",DetailOfInsurance);
        return returnMap;
    }
}
