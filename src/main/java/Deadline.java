public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public Deadline(String description, String by) {
        super(description, false);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String parseString() {
        return "D |" + super.parseString() + "| " + by; 
    }
}
