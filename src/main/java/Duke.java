//import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;
import exceptions.DukeException;
import exceptions.DukeEmptyTaskDescriptionException;
import exceptions.DukeUnknownCommandException;
import exceptions.DukeIncorrectIndexException;

public class Duke {

    Scanner sc = new Scanner(System.in);
    public ArrayList<Task> listOfTasks = new ArrayList<>();
    private String indent = "  ";
    Storage storage = new Storage("data/saved_task.txt");
    //private int index = 0;
    public boolean isEmptyString(String s) {
        return s.equals("");
    }

    public void addTodo(String task) {
        ToDo toDo = new ToDo(task);
        
        listOfTasks.add(toDo);
        gotItStatement();
        System.out.println(indent + toDo); 
        printLines();
    }

    public void gotItStatement() {
        System.out.println("Got it. I've added this task");
    }

    public void addDeadline(String taskStatement) throws DukeException {
        String[] splittedStatement = separateStrings(taskStatement);
        String description = splittedStatement[0];
        String by = splittedStatement[1];
        Deadline deadline = new Deadline(description, by);
        listOfTasks.add(deadline);
        gotItStatement();
        System.out.println(indent + deadline);
        printLines();
    }

    public void addEvent(String taskStatement) throws DukeException {
        String[] splittedStatement = separateStrings(taskStatement);
        String description = splittedStatement[0];
        String at = splittedStatement[1];
        Event event = new Event(description, at);
        listOfTasks.add(event);
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

    public void deleteTask(String description) throws DukeException {
        if(isEmptyString(description)) {
            throw new DukeIncorrectIndexException();
        }
        int index = Integer.parseInt(description);
        if (index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        Task task = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        System.out.println(indent + "Noted. I have removed this task\n" + task);
        System.out.println("Now you have " + listOfTasks.size() + " tasks in the list");
        printLines();

    }
    public boolean runCommand(String command, String description) throws DukeException {
        printLines();
        description = description.trim();

        try {

            switch (command) {
            case "bye":
                return false;
            case "list":
                printAllTasks();
                break;
            case "mark":
                markTask(description);
                break;
            case "unmark":
                unmarkTask(description);
                break;
            case "deadline":
                addDeadline(description);
                break;
            case "event":
                addEvent(description);
                break;
            case "todo":
                addTodo(description);
                break;
            case "delete":
                deleteTask(description);
                break;
            default:
                throw new DukeUnknownCommandException();
            }
        }
        catch (DukeUnknownCommandException | DukeEmptyTaskDescriptionException e) {
            System.out.println(e.getMessage());
            printLines();
        }
        return true;

    }

    public boolean checkLength(String[] stringArr, int length) {
        return stringArr.length >= length;
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

    public void markTask(String description) throws DukeException {
        if(isEmptyString(description)) {
            throw new DukeIncorrectIndexException();
        }
        int index = Integer.parseInt(description);
        if (index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        } 
        listOfTasks.get(index - 1).markIsDone();
        System.out.println("Nice, I have marked this task as done: ");
        //printLines();
        printAllTasks();
    }

    public void unmarkTask(String description) throws DukeException  {
        if(isEmptyString(description)) {
            throw new DukeIncorrectIndexException();
        }
        int index = Integer.parseInt(description);
        if (index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        listOfTasks.get(index - 1).unmarkIsDone();
        System.out.println("Ok, I have marked this task as not done yet: ");
        //printLines();
        printAllTasks();
    }

    public void printAllTasks() {
        for (int i = 0; i < listOfTasks.size(); i++) {
            int currIndex = i + 1;
            Task currTask = listOfTasks.get(i);
            System.out.println(indent + currIndex + ". " + currTask);
        }
        printLines();
    }

    public void startDuke() throws DukeException{
        entryMessage();
        listOfTasks = storage.fetch();
        String command = sc.next();
        String description = sc.nextLine();

        while (!command.equals("bye")) {
            runCommand(command, description);
            command = sc.next();
            if (!command.equals("bye")) {
                description = sc.nextLine();
            }
        }
        storage.save(listOfTasks);

        byeMessage();
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.startDuke();
    }
}
