package luke;

import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import static luke.Ui.DIVIDER;

public class Storage {

    private static String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public void save(ArrayList<Task> tasks) {

        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            // Append mode is not set to avoid duplication of data
            FileWriter fw = new FileWriter(filePath);
            // Read the tasks in the list and write them line by line into txt file
            for (Task currentTask : tasks) {
                fw.write(currentTask.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            System.out.println(DIVIDER);
        }
    }

    public static ArrayList<Task> load() throws FileNotFoundException {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }
        // create a Scanner that uses the file as its source
        Scanner input = new Scanner(file);

        while (input.hasNextLine()) {
            String line = input.nextLine();
            // Parse the input line by line and load them as tasks in Luke's TaskList
            Task currentTask = Parser.parseTaskFromStorage(line);
            tasks.add(currentTask);
        }
        input.close();

        return tasks;
    }

}
