package luke;

import luke.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles user interactions by reading user inputs, displaying messages and printing task lists.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Creates a new instance of Ui with an input scanner to read user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns the next command input by the user.
     *
     * @return The user input command as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * A horizontal divider line used for UI formatting.
     */
    public static final String DIVIDER = "-".repeat(100);

    /**
     * Prints a divider line to separate sections in the UI.
     */
    public static void showLine() {
      System.out.println(DIVIDER);
    }

    /**
     * Displays a greeting message when Luke starts.
     */
    public void showGreetingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hi there! I'm Luke a.k.a your best task manager.");
        System.out.println("I'm always here to help, so feel to ask me anything!");
        System.out.println("What can I do for you today?");
        System.out.println(DIVIDER);
    }

    /**
     * Displays a goodbye message when Luke exits.
     */
    public static void showGoodbyeMessage() {
        System.out.println("It's my pleasure to help you. Hope to see you again soon! :')");
    }

    /**
     * Displays a message indicating that a task has been marked as done.
     *
     * @param tasks The current list of tasks.
     * @param taskIndex The index of the task that was marked as done.
     */

    public static void showMarkTaskAsDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
    }

    /**
     * Displays a message indicating that a task has been marked as not done.
     *
     * @param tasks The current list of tasks.
     * @param taskIndex The index of the task that was marked as not done.
     */
    public static void showMarkTaskAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
    }

    /**
     * Displays a message when a task is added to the list.
     *
     * @param tasks The current list of tasks.
     */
    public static void showTaskAdded(ArrayList<Task> tasks) {
        System.out.println("Yay! I've added this task for you: ");
        // -1 because the taskCount has incremented after adding a new task
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Displays a message when a task is deleted from the list.
     *
     * @param tasks The list of remaining tasks after deletion.
     * @param deletedTask The task that was deleted.
     */
    public static void showTaskDeleted(ArrayList<Task> tasks, Task deletedTask) {
        System.out.println("There you go. I've removed this task for you:");
        System.out.println("  " + deletedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Prints all tasks in the task list.
     * Display a message indicating that the task list is empty if so
     *
     * @param tasks The current list of tasks.
     */
    public static void printTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("You don't have any tasks yet. Time to add one now!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i += 1) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param matchingTasks The list of matching tasks.
     */
    public static void showTasksFound(ArrayList<Task> matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println("It looks like there are no tasks with this keyword. Try a different one!");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i += 1) {
                System.out.println((i + 1) + "." + matchingTasks.get(i).toString());
            }
        }
    }

    /**
     * Displays tasks that fall on a specified date.
     *
     * @param matchingTasks The list of tasks on the given date.
     * @param date The date being searched.
     */
    public static void showTasksFoundByDate(ArrayList<Task> matchingTasks, LocalDate date) {
        if (matchingTasks.isEmpty()) {
            System.out.println("No deadlines or events found on this date.");
        } else {
            System.out.println("Here are the tasks on " + date + ":");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }

    /**
     * Displays an error message.
     *
     * @param output The error message to display.
     */
    public void showError(String output) {
        System.out.println(output);
    }
}
