package luke;

import luke.command.AddCommand;
import luke.command.Command;
import luke.command.DeleteCommand;
import luke.command.ExitCommand;
import luke.command.FindCommand;
import luke.command.FindDateCommand;
import luke.command.ListCommand;
import luke.command.MarkCommand;
import luke.command.UnmarkCommand;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Parses user commands and executes the corresponding actions.
 * This class is responsible for interpreting user inputs and converting them into command objects.
 */
public class Parser {
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param userInput The input entered by the user.
     * @return The corresponding command object
     * @throws LukeException If the user input does not match any valid command.
     */
    public static Command parse(String userInput) throws LukeException {
        // Split into two parts to extract the command keyword and its detail
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark", "unmark", "delete":
            return parseCommandWithIndex(parts, commandWord);
        case "find":
            return new FindCommand(parseFind(parts));
        case "findDate":
            return new FindDateCommand(parseFind(parts));
        case "todo":
            return new AddCommand(parseTodo(parts));
        case "deadline":
            return new AddCommand(parseDeadline(parts));
        case "event":
            return new AddCommand(parseEvent(parts));
        default:
            throw new LukeException("I'm sorry, but I don't know what that means :'(");
        }
    }

    private static Command parseCommandWithIndex(String[] parts, String commandType) throws LukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new LukeException("Oops! You forget to specify a task number to mark.");
        }
        try {
            // Convert user-inputted task number (1-based) to array index (0-based).
            // The task list shown to the user starts numbering from 1 (Ui.printTaskList method),
            // but array indices start from 0, so subtract 1.
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            switch (commandType) {
            case "mark":
                return new MarkCommand(taskIndex);
            case "unmark":
                return new UnmarkCommand(taskIndex);
            case "delete":
                return new DeleteCommand(taskIndex);
            default:
                throw new LukeException("Invalid command type: " + commandType);
            }
        } catch (NumberFormatException e) {
            throw new LukeException("Haiyaaa......The task number must be an integer.");
        }
    }


    private static String parseFind(String[] parts) throws LukeException {
        if (parts.length < 2 || parts[1].isBlank()) {
            throw new LukeException("Whoops, do you notice that you are finding nothing?");
        }
        return parts[1];
    }

    private static Todo parseTodo(String[] parts) throws LukeException {
        if (parts.length < 2) {
            throw new LukeException("Whoops, the description of a todo is empty.");
        }
        return new Todo(parts[1], false);
    }

    /**
     * Parses a deadline command and returns the corresponding Deadline object.
     * Expects the user input to follow the format: "deadline <description> /by yyyy-MM-dd HHmm",
     * extracts the description and the deadline date-time, converting the date-time string to a LocalDateTime object.
     *
     * @param parts The split user input, where the second part contains the deadline description and date-time details.
     * @return A Deadline object representing the parsed deadline.
     * @throws LukeException If the input format is incorrect or if the date-time format is wrong.
     */
    private static Deadline parseDeadline(String[] parts) throws LukeException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new LukeException("Your command format is incorrect!" + System.lineSeparator()
                    + "Please make sure to follow the format: deadline <description> /by yyyy-MM-dd HHmm");
        }
        String[] details = parts[1].split(" /by ", 2);
        try {
            LocalDateTime by = LocalDateTime.parse(details[1], INPUT_FORMAT);
            return new Deadline(details[0], by, false);
        } catch (DateTimeParseException e) {
            throw new LukeException("Oops! The date format is incorrect." + System.lineSeparator()
                    + "Please use the format: yyyy-MM-dd HHmm (e.g., 2024-03-05 1800).");
        }
    }

    /**
     * Parses an event command and returns the corresponding Event object.
     * Expects the user input to follow the format: "event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm", and
     * extracts the event details, converting the date-time strings to LocalDateTime objects.
     *
     * @param parts The split user input, where the second part contains the event description and date-time details.
     * @return An Event object representing the parsed event.
     * @throws LukeException If the input format is incorrect or if the date-time format is wrong.
     */
    private static Event parseEvent(String[] parts) throws LukeException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new LukeException("Your command format is incorrect!" + System.lineSeparator()
                    + "Please make sure to follow the format: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] details = parts[1].split(" /from ", 2);
        String description = details[0];
        String[] times = details[1].split(" /to ", 2);

        try {
            LocalDateTime from = LocalDateTime.parse(times[0], INPUT_FORMAT);
            LocalDateTime to = LocalDateTime.parse(times[1], INPUT_FORMAT);
            return new Event(description, from, to, false);
        } catch (DateTimeParseException e) {
            throw new LukeException("Oops! The date format is incorrect." + System.lineSeparator()
                    + "Please use the format: yyyy-MM-dd HHmm (e.g., 2024-03-05 1800).");
        }
    }

    /**
     * Parses a saved task from a text file and converts it into a Task object.
     *
     * @param line A stored task that is saved as a line of text.
     * @return A Task object, or null if parsing fails.
     */
    public static Task parseTaskFromStorage(String line) {
        // Splits the input line into parts based on task type format
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        try {
            switch (type) {
            case "T":
                return new Todo(description, isDone);
            case "D":
                LocalDateTime by = LocalDateTime.parse(parts[3], INPUT_FORMAT);
                return new Deadline(description, by, isDone);
            case "E":
                LocalDateTime from = LocalDateTime.parse(parts[3], INPUT_FORMAT);
                LocalDateTime to = LocalDateTime.parse(parts[4], INPUT_FORMAT);
                return new Event(description, from, to, isDone);
            default:
                // To handle cases such as having an unknown task type stored in txt file
                throw new LukeException("Unknown task type in storage.");
            }
        // Return null and let Storage.load to handle this error
        } catch (DateTimeParseException | LukeException e) {
            return null;
        }
    }
}
