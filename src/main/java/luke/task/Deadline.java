package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {

    protected LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");

    /**
     * Constructs a Deadline task with a description, deadline, and completion status.
     *
     * @param description The description of the task.
     * @param by The deadline date and time for the task.
     * @param isDone The completion status of the task (true if done, false if not done).
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the deadline date and time for the task.
     *
     * @return The deadline as a LocalDateTime.
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Returns the string representation of the deadline task.
     *
     * @return A string representing the deadline task with its status, description and deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the deadline task in a file-friendly format.
     *
     * @return A string representing the deadline task in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        return ("D" + super.toFileFormat() + " | " + by.format(INPUT_FORMAT));
    }
}
