package duke;
import java.util.ArrayList;
import duke.exceptions.DukeException;
import duke.exceptions.DukeEmptyTaskDescriptionException;
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

    public String addTodo(String task) {
        ToDo toDo = new ToDo(task);
        listOfTasks.add(toDo);
        return Ui.addTaskMessage(toDo);
    }

    public String addDeadline(String taskStatement) throws DukeException {
        if (taskStatement.equals("")) {
            throw new DukeEmptyTaskDescriptionException();
        }
        String description = Parser.getDescription(taskStatement);
        String by = Parser.getDateAndTime(taskStatement);
        Deadline deadline = new Deadline(description, by);
        listOfTasks.add(deadline);
        return Ui.addTaskMessage(deadline);
    }

    public String addEvent(String taskStatement) throws DukeException {
        String description = Parser.getDescription(taskStatement);
        String at = Parser.getDateAndTime(taskStatement);
        Event event = new Event(description, at);
        listOfTasks.add(event);
        return Ui.addTaskMessage(event);
    }

    private boolean isEmptyString(String string) {
        return string.equals("");
    }

    public String markTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if(isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        listOfTasks.get(index - 1).markIsDone();
        return Ui.markAsDone(listOfTasks.get(index - 1));
    }

    public String unmarkTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if (isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        listOfTasks.get(index - 1).unmarkIsDone();
        return Ui.markAsNotDone(listOfTasks.get(index - 1));
    }

    public String deleteTask(String description) throws DukeException {
        int index = Integer.parseInt(description);
        if(isEmptyString(description) || index > listOfTasks.size()) {
            throw new DukeIncorrectIndexException();
        }
        Task task = listOfTasks.get(index - 1);
        listOfTasks.remove(index - 1);
        return Ui.deleteMessage(task, listOfTasks.size());

    }

    public String findTask(String description, ArrayList<Task> taskList) throws DukeException {
        if (description.equals("")) {
            throw new DukeEmptyTaskDescriptionException();
        }
        ArrayList<Task> temp = new ArrayList<Task>();

        for (Task task : taskList) {
            if (task.getDescription().contains(description)) {
                temp.add(task);
            }
        }
        return Ui.findMessage(temp);
    }



}
