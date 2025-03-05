package luke.command;

import luke.Storage;
import luke.TaskList;
import luke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    }
}
