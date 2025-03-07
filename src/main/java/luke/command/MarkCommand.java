package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    protected int index;

    /**
     * Creates a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done (zero-based).
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by marking the task as done,
     * displaying a message after the task is successfully marked as done, and saving the updated list to storage.
     * If the index is out of bounds, an error message is displayed.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(index);
            Ui.showMarkTaskAsDone(tasks.getTasks(), index);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to mark a non-existent task?");
        }
    }
}
