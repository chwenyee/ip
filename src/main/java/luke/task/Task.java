package luke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        taskCount += 1;
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

    public static int getTaskCount() {
        return taskCount;
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon()+ "] " + description);
    }

    public String toFileFormat() {
        return (" | " + (isDone ? "1" : "0") + " | " + description);
    }
}


