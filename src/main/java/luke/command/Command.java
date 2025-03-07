package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents an abstract command that can be executed on the task list.
 * Subclasses of this class implement specific commands such as adding, deleting, marking and finding tasks.
 */
public abstract class Command {
    /**
     * Executes the command by performing operations on the given task list, UI, and storage.
     *
     * @param tasks   The task list to operate on.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage for saving the updated task list.
     * @throws LukeException If an error occurs during execution.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LukeException;

    /**
     * Determines if this command exits the Luke application.
     *
     * @return true if the command exits the program, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
