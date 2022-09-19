package e1;

public class DateUtilities {
    public static boolean isLeap(int year) {
        boolean isLeap;
        if (year % 4 == 0) {

            if (year % 100 == 0) {

                if (year % 400 == 0)
                    isLeap = true;
                else
                    isLeap = false;
            }

            // if the year is not century
            else
                isLeap = true;
        }

        else
            isLeap =false;
        return isLeap;
    }
    public static int numberOfDays ( int month , int year ) { /* ... */ return 1;}

    public static String convertToISODate ( String dateText ) { /* ... */ return "";}

    public static boolean checkISODate ( String ISODate ) { /* ... */ return false;}
}