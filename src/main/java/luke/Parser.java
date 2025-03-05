package luke;

import luke.command.AddCommand;
import luke.command.Command;
import luke.command.DeleteCommand;
import luke.command.ExitCommand;
import luke.command.ListCommand;
import luke.command.MarkCommand;
import luke.command.UnmarkCommand;
import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

public class Parser {
    public static Command parse(String userInput) throws LukeException {

        // Split into two parts to extract the command keyword and its detail
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        switch (commandWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(Integer.parseInt(parts[1]));
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(parts[1]));
        case "todo":
            return new AddCommand(parseTodo(parts));
        case "deadline":
            return new AddCommand(parseDeadline(parts));
        case "event":
            return new AddCommand(parseEvent(parts));
        case "delete":
            return new DeleteCommand(Integer.parseInt(parts[1]));
        default:
            throw new LukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Todo parseTodo(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("Whoops, the description of a todo is empty.");
        }
        return new Todo(parts[1], false);
    }

    private static Deadline parseDeadline(String[] parts) {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new IllegalArgumentException("Please follow deadline format: deadline <description> /by <time>");
        }
        String[] details = parts[1].split(" /by ", 2);
        return new Deadline(details[0], details[1], false);
    }

    private static Event parseEvent(String[] parts) {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new IllegalArgumentException("Please follow event format: event <description> /from <start> /to <end>");
        }
        String[] details = parts[1].split(" /from ", 2);
        String description = details[0];
        String[] times = details[1].split(" /to ", 2);
        return new Event(description, times[0], times[1], false);
    }

    // Parse a saved task in txt format into a Task object
    public static Task parseTaskFromStorage(String line) {
        // Splits the input line into parts based on task type format
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            return new Deadline(description, parts[3], isDone);
        case "E":
            return new Event(description, parts[3], parts[4], isDone);
        default:
            throw new IllegalArgumentException("Unknown task type in storage.");
        }
    }
}
