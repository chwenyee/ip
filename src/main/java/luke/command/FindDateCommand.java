package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class FindDateCommand extends Command {
    protected LocalDate date;

    public FindDateCommand(String dateString) throws LukeException {
        try {
            this.date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            throw new LukeException("Invalid date format! Please use yyyy-MM-dd.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTasksByDate(date);
        Ui.showTasksFoundByDate(matchingTasks, date);
    }
}
