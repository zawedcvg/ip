package duke;
import java.util.Scanner;
import duke.exceptions.DukeException;
import duke.exceptions.DukeEmptyTaskDescriptionException;
import duke.exceptions.DukeUnknownCommandException;

/** 
 * The main program where everthing starts
 *
 *
 *
 * */

public class Duke {

    private Scanner sc;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        storage = new Storage("data/saved_task.txt");
        sc = new Scanner(System.in);
        taskList = new TaskList(storage.fetch());
    }

    /**
     * 
     * @param command the command that is to be executed
     * @param description the description associated with a task
     * @throws DukeException
     */

    public void runCommand(String command, String description) throws DukeException {
        Ui.printLines();
        description = description.trim();

        try {

            switch (command) {
            case "list":
                Ui.printAllTasks(taskList.getTaskList());
                break;
            case "mark":
                taskList.markTask(description);
                break;
            case "unmark":
                taskList.unmarkTask(description);
                break;
            case "deadline":
                taskList.addDeadline(description);
                break;
            case "event":
                taskList.addEvent(description);
                break;
            case "todo":
                taskList.addTodo(description);
                break;
            case "delete":
                taskList.deleteTask(description);
                break;
            default:
                throw new DukeUnknownCommandException();
            }
        }
        catch (DukeUnknownCommandException | DukeEmptyTaskDescriptionException e) {
            System.out.println(e.getMessage());
            Ui.printLines();
        }
    }

    /**
     * starts the entire program
     * @throws DukeException
     */


    public void startDuke() throws DukeException{
        Ui.entryMessage();
        String command = sc.next();
        String description = sc.nextLine();

        while (!command.equals("bye")) {
            runCommand(command, description);
            command = sc.next();
            if (!command.equals("bye")) {
                description = sc.nextLine();
            }
        }
        storage.save(taskList.getTaskList());

        Ui.byeMessage();
    }


    /**
     * 
     * @param args
     * @throws DukeException
     */
    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();
        duke.startDuke();
    }
}
