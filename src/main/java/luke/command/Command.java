package luke.command;

import luke.LukeException;
import luke.Storage;
import luke.TaskList;
import luke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws LukeException;

    public boolean isExit() {
        return false;
    }
}
