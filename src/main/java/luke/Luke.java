package luke;

import luke.command.Command;
import java.io.FileNotFoundException;

/**
 * The main class for Luke CLI program, a task manager.
 * This class initializes the necessary components, such as loading saved tasks from a text file,
 * and then runs the program.
 */
public class Luke {

    private Storage storage;
    private Ui ui;
    private TaskList tasks;

    /**
     * Initializes a Luke instance.
     * This sets up the user interface, storage and loads task from the text file.
     *
     * @param filePath The file path where the tasks are stored.
     * @throws RuntimeException if the task file is not found.
     */
    public Luke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(Storage.load());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Runs the Luke CLI program.
     * This method continuously reads user commands, processes them, and executes the corresponding actions.
     * Luke exits only when the user inputs 'bye'.
     */
    public void run() {
        ui.showGreetingMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                // show the divider line ("-----")
                Ui.showLine();
                Command c = Parser.parse(fullCommand);
                // Executes the command, displays messages, and updates the task file accordingly.
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LukeException e) {
                ui.showError(e.getMessage());
            } finally {
                Ui.showLine();
            }
        }
    }

    /**
     * The entry point of the Luke application.
     * Initializes and starts the program.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // A relative path is used for the txt file that stores the tasks
        new Luke("data/tasks.txt").run();
    }

}
