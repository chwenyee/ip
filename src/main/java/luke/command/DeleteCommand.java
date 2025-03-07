package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    protected int index;

    /**
     * Creates a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (zero-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command by deleting the task at the specified index,
     * displaying a message after the specified task is deleted, and saving the updated list to storage.
     * If the index is out of bounds, an error message is displayed.
     *
     * @param tasks   The task list where the task will be deleted.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(index);
            Ui.showTaskDeleted(tasks.getTasks(), removedTask);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to delete a non-existent task?");
        }
    }
}
