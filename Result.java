import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
     */

    public static String findDay(int month, int day, int year) {
        
        Date date = new Date(year, month,day);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);


        switch(calendar.get(Calendar.DAY_OF_WEEK)){
            case 5:
            return "SUNDAY";
            case 6:
                return "MONDAY";
                case 0:
                return "TUESDAY";
                
                case 1:
                return "WEDNESDAY";
                case 2:
                return "THURDAY";
                case 3:
                return "FRIDAY";
                case 4:
                return "SATURDAY";
               
    }
    return "error";
    } 

}