package duke;
public class Event extends Task {

    protected String at;

    public Event(String description, String at, boolean isDone) {
        super(description, isDone);
        this.at = at;
    }

    public Event(String description, String at) {
        super(description, false);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public String parseString() {
        return "E |" + super.parseString() + " | " + at; 
    }
}
