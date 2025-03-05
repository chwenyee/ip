package luke;

import luke.command.Command;
import java.io.FileNotFoundException;


public class Luke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(Storage.load());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        ui.showGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                /* Execute the command line, display message and update the txt file after parsing and
                   determining what type of command has to be executed */
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LukeException e) {
                // To get the error message from LukeException
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        // A relative path is used for the txt file that stores the tasks
        new Luke("data/tasks.txt").run();
    }

}
