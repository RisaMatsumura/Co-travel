package risamatsumura.github.com.co_travel.utils;

import java.util.Calendar;

/**
 * Created by risam on 05/10/2015.
 */
public class DateFormatter {

    public static String getMDString(Calendar date) {

        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        String dateStr = month + 1 + "-" + day;
        return dateStr;
    }

    public static String getMDYString(Calendar date) {

        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);

        String dateStr = month + 1 + "-" + day + "-" + year;
        return dateStr;
    }

    public static String getMDYString(String storedDate) {

        long miliseconds = Long.valueOf(storedDate);
        Calendar date = Calendar.getInstance();
        date.setTimeInMillis(miliseconds);

        return getMDYString(date);
    }


    public String getTimeInMillis(int year, int month, int day) {
        Calendar date = Calendar.getInstance();
        date.set(year, month, day);
        long timeInMillis = date.getTimeInMillis();
        return Long.toString(timeInMillis);
    }

    public static String getTimeInMillis(Calendar date) {
        long timeInMillis = date.getTimeInMillis();
        return Long.toString(timeInMillis);
    }

}
