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

import static luke.Luke.DIVIDER;

public class Storage {

    private static final String FILE_PATH = "data/luke.txt";

    public static void saveTasksToFile(ArrayList<Task> tasks) {

        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();

            // Append mode is not set to avoid duplication of data
            FileWriter fw = new FileWriter(FILE_PATH);
            // Read the tasks in the list and write them line by line into luke.txt
            for (Task currentTask : tasks) {
                fw.write(currentTask.toFileFormat() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
            System.out.println(DIVIDER);
        }
    }

    public static ArrayList<Task> loadTasksFromFile() {

        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                // create a Scanner using the file as source
                String line = input.nextLine();
                Task currentTask = parseTask(line);
                if (currentTask != null) {
                    tasks.add(currentTask);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading your file.");
        }
        return tasks;
    }

    // Parse a saved task in txt format into a Task object
    private static Task parseTask(String line) {

        // Splits the input line into parts based on task type format
        String[] parts = line.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
        case "T":
            return new Todo(parts[2], isDone);
        case "D":
            return new Deadline(parts[2], parts[3], isDone);
        case "E":
            return new Event(parts[2], parts[3], parts[4], isDone);
        default:
            return null;
        }
    }
}
