package com.xtalk.server.xtalkserver.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author xin.z
 * @date 2021/1/20 5:29 下午
 */
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd HH:mm:ss";

    public static String getTime(){
        LocalDate date = LocalDate.now();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return date.format(fmt);
    }


    public static Date parseDate(String dateStr, String format) {
        Date date = null;
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (StringUtils.isNotBlank(dateStr)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            try {
                date = dateFormat.parse(dateStr);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return date;
    }
    public static String formatDate(Date date, String format) {
        String dateStr = null;
        if (StringUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        if (date != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            dateStr = dateFormat.format(date);
        }
        return dateStr;
    }
}
