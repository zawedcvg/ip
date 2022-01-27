package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Parser {

    /**
     * splits the date and time
     * @param description the task description
     * @return array containing the sate and time split
     */
    public static String[] splitDateAndTime(String description) {
        return description.split(" ", 2);
    }

    /**
     * 
     * @param description the task description
     * @return time mentioned in the DukeEmptyTaskDescriptionException
     */
    public static String getTime(String description) {
        return splitDateAndTime(description)[1].trim();
    }

    /**
     * 
     * @param description the task description
     * @return the date of the task
     */

    public static String getDate(String description) {
        LocalDate localDate = LocalDate.parse(splitDateAndTime(description)[0].trim());
        return localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * 
     * @param taskStatement the task description
     * @return string array containing the main description and the peripheral information about date and time
     */

    public static String[] splitTaskStatement(String taskStatement) {
        String[] splitted = taskStatement.split("/", 2);
        splitted[1] = splitted[1].trim().split(" ", 2)[1];
        //System.out.println(splitted[1]);
        return splitted;
    }

    /**
     * 
     * @param taskStatement the task description
     * @return the string containing the main description
     */

    public static String getDescription(String taskStatement) {
        return splitTaskStatement(taskStatement)[0];
    }

    /**
     * 
     * @param taskStatement the task description
     * @return get the date and time related to the task
     */

    public static String getDateAndTime(String taskStatement) {
        String description = splitTaskStatement(taskStatement)[1];
        return getDate(description) + " " + getTime(description);
    }
}

