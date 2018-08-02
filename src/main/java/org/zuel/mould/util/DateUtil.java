package org.zuel.mould.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private final static SimpleDateFormat dateFormatStandard = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final static SimpleDateFormat dateFormatMini = new SimpleDateFormat("yyyyMMddHHmmss");

    private final static SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");

    public static String getDateStrStandard(Date date) {

        return dateFormatStandard.format(date);
    }

    public static String getDateStrMini(Date date) {

        return dateFormatMini.format(date);
    }

    public static String getDateStrDate(Date date) {

        return dateFormatDate.format(date);
    }
}
