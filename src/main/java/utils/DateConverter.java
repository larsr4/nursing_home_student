package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Date;



public class DateConverter {
    /**
     * Ändert die Ankommenden String in Local DAte
     * @param date
     * @return LocalDate
     */
    public static LocalDate convertStringToLocalDate(String date) {
        String[] array = date.split("-");
        LocalDate result = LocalDate.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]),
                Integer.parseInt(array[2]));
        return result;
    }

    public static LocalTime convertStringToLocalTime(String time) {
        String[] array = time.split(":");
        LocalTime result = LocalTime.of(Integer.parseInt(array[0]), Integer.parseInt(array[1]));
        return result;
    }
    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
    /**
     * Gibt das Date now in GZ aus
     * @return Date im INT
     */
    public static int getDatenowINT(){
        LocalDate today = LocalDate.now();
        String[] arr = today.toString().split("-");
        return Integer.parseInt(arr[0]);
    }

}
