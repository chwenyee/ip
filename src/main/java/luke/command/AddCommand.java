package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LukeException {
        tasks.addTask(task);
        Ui.showTaskAdded(tasks.getTasks());
        storage.save(tasks.getTasks());
    }
}
