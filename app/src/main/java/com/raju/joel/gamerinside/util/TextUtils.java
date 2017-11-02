package com.raju.joel.gamerinside.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Joel on 24-Oct-17.
 */

public class TextUtils {

    public static String getFormattedDateStringFromUnixEpoch(String dateString, String dateFormat) {
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

    public static String getFormattedDateForArticleFromUnixEpoch(String dataString) {
        if (dataString == null || dataString.isEmpty()) {
            return null;
        }
        String timeSuffix = "ago";
        Date dateToFormat;
        Date currentDate = new Date();
        try {
            dateToFormat = new Date(Long.parseLong(dataString));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            dateToFormat = new Date();
        }

        long duration = currentDate.getTime() - dateToFormat.getTime();

        int durationInHours =   (int)TimeUnit.MILLISECONDS.toHours(duration);
        int durationInMinutes = (int)TimeUnit.MILLISECONDS.toMinutes(duration);

        if (durationInHours > 24) {
            return dateToFormat.toString();
        } else if (durationInHours > 1 && durationInHours < 24) {
            return String.valueOf(durationInHours) + " hours " + timeSuffix;
        } else if (durationInHours == 1) {
            return String.valueOf(durationInHours) + " hour " + timeSuffix;
        } else if (durationInMinutes > 1) {
            return String.valueOf(durationInMinutes) + " minutes " + timeSuffix;
        } else if (durationInMinutes == 1) {
            return String.valueOf(durationInMinutes) + " minute " + timeSuffix;
        } else {
            return "just now";
        }
    }
}
