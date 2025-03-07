package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

/**
 * Represents a command to exit the Luke application.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command by displaying a goodbye message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface for displaying messages.
     * @param storage The storage that saves tasks inputted by users (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Ui.showGoodbyeMessage();
    }

    /**
     * Indicates that this command exits the Luke application.
     *
     * @return true, as this command is for exiting.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}