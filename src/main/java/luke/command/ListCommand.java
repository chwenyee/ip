package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents a command to print out all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command by retrieving the tasks from TaskList,
     * and displaying all of them.
     *
     * @param tasks   The task list containing all tasks.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage that saves tasks inputted by users (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.printTaskList(tasks.getTasks());
    }
}