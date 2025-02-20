package luke.task;

public class Task {

    protected String description;
    protected boolean isDone;
    protected static int taskCount = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
}


