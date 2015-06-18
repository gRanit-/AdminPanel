package granicki.web.adminpanel.controllers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by mc109881 on 2015-06-10.
 */
public class Main {


    public static void main(String[] args) {

        DateFormat format = new SimpleDateFormat("MM-yyyy", Locale.ENGLISH);

        try {
            Date date = format.parse("02-2015");
            System.out.println(date.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
