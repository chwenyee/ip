package luke.task;

/**
 * Represents a task that does not have a specific deadline or time range.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task (true if done, false if not done).
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns the string representation of the Todo task.
     *
     * @return A string representing the Todo task with its status and description.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns the string representation of the Todo task in a file-friendly format.
     *
     * @return A string representing the Todo task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        return ("T" + super.toFileFormat());
    }
}
