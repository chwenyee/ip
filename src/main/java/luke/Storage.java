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

            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                fw.write(task.toFileFormat() + System.lineSeparator());
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

        if (!file.exists()) {
            System.out.println("There is no existing file.");
            System.out.println(DIVIDER);
            return tasks;
        }

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                // create a Scanner using the file as source
                String line = input.nextLine();
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading your file.");
        }
        return tasks;
    }

    public static void printTasksFromFile() {

        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("There is no existing file.");
            System.out.println(DIVIDER);
        }

        try {
            Scanner input = new Scanner(file);
            while (input.hasNextLine()) {
                System.out.println(input.nextLine());
            }
            input.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // Parse a saved task in txt format into a Task object
    private static Task parseTask(String line) {

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
