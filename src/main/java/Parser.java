import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Parser {

    public static String[] splitDateAndTime(String description) {
        return description.split(" ", 2);
    }
    public static String getTime(String description) {
        return splitDateAndTime(description)[1].trim();
    }

    public static String getDate(String description) {
        LocalDate localDate = LocalDate.parse(splitDateAndTime(description)[0].trim());
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String[] splitTaskStatement(String taskStatement) {
        String[] splitted = taskStatement.split("/", 2);
        splitted[1] = splitted[1].trim().split(" ", 2)[1];
        //System.out.println(splitted[1]);
        return splitted;
    }

    public static String getDescription(String taskStatement) {
        return splitTaskStatement(taskStatement)[0];
    }

    public static String getDateAndTime(String taskStatement) {
        String description = splitTaskStatement(taskStatement)[1];
        return getDate(description) + " " + getTime(description);
    }

    //public static 
}
