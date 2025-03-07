package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    protected Task task;

    /**
     * Creates an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list,
     * displaying a message after the task is successfully added, and saving the updated list to storage.
     *
     * @param tasks   The task list to add the task to.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     * @throws LukeException If the task list exceeds the maximum limit, i.e. 100.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LukeException {
        tasks.addTask(task);
        Ui.showTaskAdded(tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
