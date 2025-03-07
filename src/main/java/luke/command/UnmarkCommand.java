package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents a command to unmark a task (mark it as not done).
 */
public class UnmarkCommand extends Command {
    protected int index;

    /**
     * Creates an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to be unmarked (zero-based).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as not done,
     * displaying a message after the task is successfully marked as not done, and saving the updated list to storage.
     * If the index is out of bounds, an error message is displayed.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(index);
            Ui.showMarkTaskAsNotDone(tasks.getTasks(), index);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to unmark a non-existent task?");
        }
    }
}
