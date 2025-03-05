package luke;

import luke.command.AddCommand;
import luke.command.Command;
import luke.command.DeleteCommand;
import luke.command.ExitCommand;
import luke.command.FindCommand;
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
        case "mark", "unmark", "delete":
            return parseCommandWithIndex(parts, commandWord);
        case "find":
            return new FindCommand(parseFind(parts));
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
            int taskIndex = Integer.parseInt(parts[1]);
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
            throw new LukeException("Aiyo......The task number must be an integer.");
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

    private static Deadline parseDeadline(String[] parts) throws LukeException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new LukeException("Your command format is incorrect!" + System.lineSeparator()
                    + "Please make sure to follow the format: deadline <description> /by <time>");
        }
        String[] details = parts[1].split(" /by ", 2);
        return new Deadline(details[0], details[1], false);
    }

    private static Event parseEvent(String[] parts) throws LukeException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new LukeException("Your command format is incorrect!" + System.lineSeparator()
                    + "Please make sure to follow the format: event <description> /from <start> /to <end>");
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
            // To handle abnormal case such as having a non-desired task type stored in txt file
            throw new IllegalArgumentException("Unknown task type in storage.");
        }
    }
}
