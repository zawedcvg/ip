package duke;
import java.util.ArrayList;
import duke.exceptions.DukeException;
import duke.exceptions.DukeEmptyTaskDescriptionException;
import duke.exceptions.DukeUnknownCommandException;
import duke.exceptions.DukeIncorrectIndexException;
public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public ArrayList<Task> getTaskList() {
        return this.listOfTasks;
    }

    public void addTodo(String task) {
        ToDo toDo = new ToDo(task);
        listOfTasks.add(toDo);
        Ui.addTaskMessage(toDo);
    }

    public void addDeadline(String taskStatement) throws DukeException {
        String description = Parser.getDescription(taskStatement);
        String by = Parser.getDateAndTime(taskStatement);
        Deadline deadline = new Deadline(description, by);
        listOfTasks.add(deadline);
        Ui.addTaskMessage(deadline);
    }

    public void addEvent(String taskStatement) throws DukeException {
        String description = Parser.getDescription(taskStatement);
        String at = Parser.getDateAndTime(taskStatement);
        Event event = new Event(description, at);
        listOfTasks.add(event);
        Ui.addTaskMessage(event);
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    public void markTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if(isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        listOfTasks.get(index - 1).markIsDone();
        Ui.markAsDone(listOfTasks);
    }

    public void unmarkTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if (isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        listOfTasks.get(index - 1).unmarkIsDone();
        Ui.markAsNotDone(listOfTasks);
    }

    public void deleteTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if(isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        Task task = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        Ui.deleteMessage(task, listOfTasks.size());

    }


}
