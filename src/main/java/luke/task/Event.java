package luke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that occurs within a range of time.
 */
public class Event extends Task {

    protected LocalDateTime from;
    protected LocalDateTime to;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a");


    /**
     * Constructs an Event task with a description, start and end time, and completion status.
     *
     * @param description The description of the event.
     * @param from The start date and time for the event.
     * @param to The end date and time for the event.
     * @param isDone The completion status of the event (true if done, false if not done).
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start date and time for the event.
     *
     * @return The start date and time as a LocalDateTime.
     */
    public LocalDateTime getFrom() {
        return from;
    }

    /**
     * Returns the end date and time for the event.
     *
     * @return The end date and time as a LocalDateTime.
     */
    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return A string representing the event with its status, description, start and end time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(OUTPUT_FORMAT) + " to: " + to.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Returns the string representation of the event in a file-friendly format.
     *
     * @return A string representing the event in a file-friendly format.
     */
    @Override
    public String toFileFormat() {
        return ("E" + super.toFileFormat() + " | " + from.format(INPUT_FORMAT) + " | " + to.format(INPUT_FORMAT));
    }

}
