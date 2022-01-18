import java.util.Scanner;

public class Duke {

    Scanner sc = new Scanner(System.in);
    public Task[] listOfTasks = new Task[100];
    private String indent = "  ";
    private int index = 0;


    public void addString(String task) {
        listOfTasks[index] = new Task(task);
        index++;
        System.out.println(indent + "added " + task);
    }

    public boolean runCommand(String command) {
        printLines();
        //int length = command.length();
        //String[] halveStrings = command.split(" ");

        if (command.equals("bye")) {
            return false;
        } else if (command.equals("list")) {
            printAllTasks();
        } /*else if (halveStrings[0].equals("mark")) {*/
            /*markTask(Integer.parseInt(halveStrings[1]));*/
        /*} else if (halveStrings[0].equals("unmark")) {*/
            /*unmarkTask(Integer.parseInt(halveStrings[1]));*/
        /*}*/ else {
            addString(command);
        }

        printLines();
        return true;
    }

    public static void entryMessage() {
        printLines();
        System.out.println("Hello I'm Duke\nWhat can I do for you today?");
        printLines();
    }

    public static void byeMessage() {
        printLines();
        System.out.println("Bye, see you next time");
        printLines();
    }

    public static void printLines() {
        System.out.println("---------------------------------------------");
    }

    //public void markTask(int index) {
        //listOfTasks[index - 1].markIsDone();
    //}

    //public void unmarkTask(int index) {
        //listOfTasks[index - 1].unmarkIsDone();
    //}

    public void printAllTasks() {
        for (int i = 0; i < index; i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks[i];
            System.out.println(indent + currIndex + ". " + currTask);
        }
    }

    public void startDuke() {
        entryMessage();
        boolean loop = true;

        while (loop) {
            String command = sc.nextLine();
            loop = runCommand(command);
        }

        byeMessage();
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.startDuke();
    }
}
