package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;
import luke.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LukeException {
        ArrayList<Task> matchingTasks = tasks.findTask(keyword);
        Ui.showTasksFound(matchingTasks);
    }
}
