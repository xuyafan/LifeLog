package com.github.xuyafan.lifelog.utils;

import com.github.xuyafan.lifelog.data.Record;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by xuyafan on 2017/12/16.
 */

public class DateUtil {

    public static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");

    public static final SimpleDateFormat YEAR_MONTH_FORMAT = new SimpleDateFormat("yyyy-MM");

    public static String DateFormat(Date date) {

        return sdf.format(date);
    }

    public static boolean isSameDay(Date date1, Date date2) {

        return DAY_FORMAT.format(date1).equals(DAY_FORMAT.format(date2));
    }





}
