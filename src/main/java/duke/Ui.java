package duke;
import java.util.ArrayList;

public class Ui {

    private static String indent = " ";

    public static String gotItStatement() {
        return("Got it. I've added this task\n");
    }

    public static String entryMessage() {
        return(printLines() + "Hello I'm Duke\nWhat can I do for you today?" + printLines());
    }

    public static String addTaskMessage(Task task) {
        return (gotItStatement() + printTask(task) + printLines());
    }

    public static String byeMessage() {
        return("Bye, see you next time" + printLines());
    }

    public static String printLines() {
        return("\n");
    }

    public static String markAsDone(Task task) {
        return("Nice I have marked this task as done \n" + task);
    }
    public static String markAsNotDone(Task task) {
        return("Ok, I have marked this task as not done: \n" + task);
    }

    public static String printAllTasks(ArrayList<Task> listOfTasks) {
        String taskString = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            taskString += indent + currIndex + ". " + currTask + "\n";
        }
        return taskString + printLines();
    }

    public static String deleteMessage(Task task, int size) {
        return(indent + "Noted. I have removed this task\n" + task + printSize(size) + printLines());

    }

    /**
     * function to print message on running find
     * @param listOfTasks list of tasks that match the description
     */
    public static String findMessage(ArrayList<Task> listOfTasks) {
        String taskString = "";
        for (Task task : listOfTasks) {
            taskString += indent + task;
        }
        return("Noted. I have found this tasks matching your description\n" + taskString + printLines());

    }

    public static String showMessage(String message) {
        return(message);
    }


    public static String printTask(Task task) {
        return(indent + task);
    }

    public static String printSize(int size) {
        return("Now you have " + size + " tasks in the list\n");

    }
}
