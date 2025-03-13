package luke.command;

import luke.exception.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a command to find tasks occurring on a specific date.
 */
public class FindDateCommand extends Command {
    protected LocalDate date;

    /**
     * Creates a FindDateCommand with the specified date.
     *
     * @param dateString The date to search for, in yyyy-MM-dd format.
     * @throws LukeException If the date format is invalid.
     */
    public FindDateCommand(String dateString) throws LukeException {
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid date format! Please use yyyy-MM-dd.");
        }
    }

    /**
     * Executes the command by searching for tasks matching the specified date
     * and displaying the results.
     *
     * @param tasks   The task list to search in.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage that saves tasks inputted by users (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasksByDate(date);
        Ui.showTasksFoundByDate(matchingTasks, date);
    }
}
