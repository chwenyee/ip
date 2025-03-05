package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markTask(index - 1);
            ui.showMarkTaskAsDone(tasks.getTasks(), index - 1);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to mark a non-existent task?");
        }
    }
}
