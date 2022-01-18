//import java.util.Arrays;
import java.util.Scanner;

public class Duke {

    Scanner sc = new Scanner(System.in);
    public Task[] listOfTasks = new Task[100];
    private String indent = "  ";
    private int index = 0;


    public void addTodo(String task) {
        ToDo toDo = new ToDo(task);
        listOfTasks[index] = toDo;
        index++;
        gotItStatement();
        System.out.println(indent + toDo); 
        printLines();
    }

    public void gotItStatement() {
        System.out.println("Got it. I've added this task");
    }

    public void addDeadline(String taskStatement) {
        String[] splittedStatement = separateStrings(taskStatement);
        String description = splittedStatement[0];
        String by = splittedStatement[1];
        Deadline deadline = new Deadline(description, by);
        listOfTasks[index] = deadline;
        index++;
        gotItStatement();
        System.out.println(indent + deadline);
        printLines();
    }

    public void addEvent(String taskStatement) {
        String[] splittedStatement = separateStrings(taskStatement);
        String description = splittedStatement[0];
        String at = splittedStatement[1];
        Event event = new Event(description, at);
        listOfTasks[index] = event;
        index++;
        gotItStatement();
        System.out.println(indent + event);
        printLines();
    }

    public String[] separateStrings(String taskStatement) {
        String[] splitted = taskStatement.split("/", 2);
        splitted[1] = splitted[1].trim().split(" ", 2)[1];
        //System.out.println(splitted[1]);
        return splitted;
    }

    public boolean runCommand(String command) {
        printLines();
        String[] halveStrings = command.split(" ", 2);
        String typeTask = halveStrings[0];

        switch (typeTask) {
        case "bye":
            return false;
        case "list":
            printAllTasks();
            break;
        case "mark":
            markTask(Integer.parseInt(halveStrings[1]));
            break;
        case "unmark":
            unmarkTask(Integer.parseInt(halveStrings[1]));
            break;
        case "deadline":
            addDeadline(halveStrings[1]);
            break;
        case "event":
            addEvent(halveStrings[1]);
            break;
        case "todo":
            addTodo(typeTask);
            break;
        default:
            System.out.println("Incorrect type. Add a valid option");
            printLines();
        }

        return true;
    }

    public static void entryMessage() {
        printLines();
        System.out.println("Hello I'm Duke\nWhat can I do for you today?");
        printLines();
    }

    public static void byeMessage() {
        System.out.println("Bye, see you next time");
        printLines();
    }

    public static void printLines() {
        System.out.println("---------------------------------------------");
    }

    public void markTask(int index) {
        listOfTasks[index - 1].markIsDone();
        System.out.println("Nice, I have marked this task as done: ");
        //printLines();
        printAllTasks();
    }

    public void unmarkTask(int index) {
        listOfTasks[index - 1].unmarkIsDone();
        System.out.println("Ok, I have marked this task as not done yet: ");
        //printLines();
        printAllTasks();
    }

    public void printAllTasks() {
        for (int i = 0; i < index; i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks[i];
            System.out.println(indent + currIndex + ". " + currTask);
        }
        printLines();
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
