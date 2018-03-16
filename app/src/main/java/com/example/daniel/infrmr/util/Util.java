package com.example.daniel.infrmr.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Daniel on 3/7/2018.
 */

public class Util {

    public static String dateFormatted(String dateString){

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = dateFormat.parse(dateString);


        } catch (ParseException e) {

            e.printStackTrace();
        }

        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy");

        return format.format(date);
    }
}
