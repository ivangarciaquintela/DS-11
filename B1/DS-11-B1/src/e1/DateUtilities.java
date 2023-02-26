package e1;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;

public class DateUtilities {
    public static boolean isLeap(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0)
                    return true;
            }
            // if the year is not century
            else
                return true;
        }
        return false;
    }

    public static int numberOfDays ( int month , int year ) {
        if(month>12 || month ==0)
            throw new IllegalArgumentException("El mes introducido es incorrecto.\n");

        switch (month){
            case 1: case 3 : case 5: case 7: case 8:case 10:case 12:
                return 31;
            case 2:
                return (isLeap(year))?29:28;
            default:
                return 30;
        }
    }


    public static String convertToISODate ( String dateText ) {
            String aux = dateText.replace(","," ");
            String[] buff = aux.split(" ");
            String month;
            String day = buff[1];
            String year = buff[3];
            switch (buff[0].toLowerCase()){
                case "january":
                    month = "01";
                    break;
                case "february":
                    month = "02";
                    break;
                case "march":
                    month = "03";
                    break;
                case "april":
                    month = "04";
                    break;
                case "may":
                    month = "05";
                    break;
                case "june":
                    month = "06";
                    break;
                case "july":
                    month = "07";
                    break;
                case "august":
                    month = "08";
                    break;
                case "september":
                    month = "09";
                    break;
                case "october":
                    month = "10";
                    break;
                case "november":
                    month = "11";
                    break;
                case "december":
                    month = "12";
                    break;
                default:
                    month = "0";
                    break;
            }
            if(Integer.parseInt(month) == 0
                    || Integer.parseInt(day) > numberOfDays(Integer.parseInt(month), Integer.parseInt(year))
                    ||Integer.parseInt(day) < 1
                    || Integer.parseInt(year)<0)
                throw new IllegalArgumentException("Datos introducidos incorrectos");
        return year+"-"+month+"-"+day;
    }


    public static boolean checkISODate ( String ISODate ) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try{
            Date d  =format.parse(ISODate);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}