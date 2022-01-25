import java.util.ArrayList;

public class Ui {

    private static String indent = " ";

    public static void gotItStatement() {
        System.out.println("Got it. I've added this task");
    }

    public static void entryMessage() {
        printLines();
        System.out.println("Hello I'm Duke\nWhat can I do for you today?");
        printLines();
    }

    public static void addTaskMessage(Task task) {
        gotItStatement();
        printTask(task);
        printLines();
    }

    public static void byeMessage() {
        System.out.println("Bye, see you next time");
        printLines();
    }

    public static void printLines() {
        System.out.println("---------------------------------------------");
    }

    public static void markAsDone(ArrayList<Task> listOfTasks) {
        System.out.println("Nice I have marked this task as done");
        printAllTasks(listOfTasks);
    }
    public static void markAsNotDone(ArrayList<Task> listOfTasks) {
        System.out.println("Ok, I have marked this task as not done: ");
        printAllTasks(listOfTasks);
    }

    public static void printAllTasks(ArrayList<Task> listOfTasks) {
        for (int i = 0; i < listOfTasks.size(); i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            System.out.println(indent + currIndex + ". " + currTask);
        }
        printLines();
    }

    public static void deleteMessage(Task task, int size) {
        System.out.println(indent + "Noted. I have removed this task\n" + task);
        printSize(size);
        printLines();

    }

    public static void printTask(Task task) {
        System.out.println(indent + task);
    }

    public static void printSize(int size) {
        System.out.println("Now you have " + size + " tasks in the list");

    }
}
