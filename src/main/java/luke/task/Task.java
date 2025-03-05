package luke.task;

public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon()+ "] " + description);
    }

    public String toFileFormat() {
        return (" | " + (isDone ? "1" : "0") + " | " + description);
    }
}


