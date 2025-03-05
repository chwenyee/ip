package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task removedTask = tasks.deleteTask(index - 1);
            Ui.showTaskDeleted(tasks.getTasks(), removedTask);
            storage.save(tasks.getTasks());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("Hmm... Why are you trying to delete a non-existent task?");
        }
    }
}
