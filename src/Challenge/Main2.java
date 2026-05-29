package Challenge;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

// kohtumine peab toimuma tööpäeval
// kella 7.00-20.00 vahel
// üks töötaja elab Ameerika idarannikul, teine Sydneis
// 10 päeva puhul, millal nad saavad kohtuda?
public class Main2 {

    public static void main(String[] args) {

        ZonedDateTime current = ZonedDateTime.now(ZoneId.of("UTC"));

        List<String> availableAUTimes = new ArrayList<>();
        List<String> availableUSTimes = new ArrayList<>();

        for (int i = 24; i < 24 * 9; i++) {
            ZonedDateTime step = current.plusHours(i);
            ZonedDateTime usEmployee = step.withZoneSameInstant(
                    ZoneId.of("America/New_York"));

            ZonedDateTime auEmployee = step.withZoneSameInstant(
                    ZoneId.of("Australia/Sydney"));

            if(isWorkingTime(usEmployee) && isWorkingTime(auEmployee)) {
                availableAUTimes.add(auEmployee.toString());
                availableUSTimes.add(usEmployee.toString());
            }
        }
        System.out.println("Available AU times:");
        availableAUTimes.forEach(System.out::println);
        System.out.println("Available US times:");
        availableUSTimes.forEach(System.out::println);

    }


    public static boolean isWorkingTime(ZonedDateTime time) {
        boolean isWeekday =
                time.getDayOfWeek() != DayOfWeek.SATURDAY &&
                        time.getDayOfWeek() != DayOfWeek.SUNDAY;

        boolean isWorkingHour =
                time.getHour() >= 7 && time.getHour() < 20;

        return isWeekday && isWorkingHour;
    }
}
