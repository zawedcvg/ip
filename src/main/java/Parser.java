import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Parser {

    public static String[] splitDescriptionAndTime(String description) {
        return description.split(" ");
    }
    public static String getTime(String description) {
        return splitDescriptionAndTime(description)[1].trim();
    }

    public static String getDate(String description) {
        LocalDate localDate = LocalDate.parse(splitDescriptionAndTime(description)[0].trim());
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
