package com.raju.joel.gamerinside.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Joel on 24-Oct-17.
 */

public class TextUtils {

    public static String getFormatedDateStringFromUnixEpoch(String dateString, String dateFormat) {
        Date date = null;
        DateFormat format = new SimpleDateFormat(dateFormat);
        try {
            date = new Date(Long.parseLong(dateString));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            date = new Date();
        }
        return format.format(date);
    }

    public static String formatStringToGivenLength(String input, int maxLength) {
        input = (input.length() > maxLength) ? input.substring(0, maxLength) : input;
        return input;
    }
}
