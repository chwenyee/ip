package luke.task;

/**
 * Represents a task with a description and its completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a Task with a given description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task (true if done, false if not done).
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the icon representing the task's completion status.
     *
     * @return "X" if the task is done, a space character if the task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Gets the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string representing the task with its status and description.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon()+ "] " + description);
    }

    /**
     * Returns the string representation of the task in a file-friendly format.
     * File-friendly format for the Luke Application is as follows:
     * - Delimiters (in this case, '|' character) is used to separate different attributes of the task
     * - The task completion status, represented as "1" for done or "0" for not done.
     * - A description of the task.
     *
     * @return A string representing the task in a file-friendly format.
     */
    public String toFileFormat() {
        return (" | " + (isDone ? "1" : "0") + " | " + description);
    }
}


