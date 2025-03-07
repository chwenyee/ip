package luke;

import luke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storage operations for the Luke application.
 * This class saves tasks to a text file and loads them when the application starts.
 */
public class Storage {

    private static String filePath;

    /**
     * Initializes a Storage instance with the specified file path.
     *
     * @param filePath The file path where tasks are stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the given list of tasks to a text file.
     * If the file does not exist, it is created automatically.
     *
     * @param tasks The file path where the tasks are stored.
     */
    public void save(ArrayList<Task> tasks) {

        try {
            File file = new File(filePath);
            // Creates the parent directory if it does not exist.
            file.getParentFile().mkdirs();

            // Overwrites the file instead of using append mode to prevent data duplication
            FileWriter fw = new FileWriter(filePath);
            // Writes each task in a format suitable for storage
            for (Task currentTask : tasks) {
                fw.write(currentTask.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            Ui.showLine();
        }
    }

    /**
     * Loads saved tasks from the storage file.
     * If the file does not exist, an empty list is returned.
     *
     * @return A list of tasks retrieved from the storage file.
     * @throws FileNotFoundException If the task file is not found.
     */
    public static ArrayList<Task> load() throws FileNotFoundException{

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        Scanner input = new Scanner(file);
        // Reads file content and converts each line into a task
        while (input.hasNextLine()) {
            String line = input.nextLine();
            Task currentTask = Parser.parseTaskFromStorage(line);
            // currentTask is returned to be null if the current saved task is written in wrong format.
            if (currentTask != null) {
                tasks.add(currentTask);
            // null currentTask will not be added. Instead, an error message is displayed.
            } else {
                Ui.showLine();
                System.out.println("Skipping invalid task in storage: " + line + System.lineSeparator()
                        + "This could be due to wrong format.");
            }
        }
        input.close();

        return tasks;
    }

}
