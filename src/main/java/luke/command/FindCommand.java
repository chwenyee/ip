package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.util.ArrayList;

/**
 * Represents a command to find tasks that match a given keyword.
 */
public class FindCommand extends Command {
    protected String keyword;

    /**
     * Creates a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command by searching for tasks containing the keyword
     * and displaying the results.
     *
     * @param tasks   The task list to search in.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage that saves tasks inputted by users (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = tasks.findTask(keyword);
        Ui.showTasksFound(matchingTasks);
    }
}
