package luke.task;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toFileFormat() {
        return ("D" + super.toFileFormat() + " | " + by);
    }
}
