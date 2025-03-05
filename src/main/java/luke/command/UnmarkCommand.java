package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

public class UnmarkCommand extends Command {

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmarkTask(index - 1);
            Ui.showMarkTaskAsNotDone(tasks.getTasks(), index - 1);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to unmark a non-existent task?");
        }
    }
}
