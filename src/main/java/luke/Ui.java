package luke;

import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public static final String DIVIDER = "-".repeat(100);

    public void showLine() {
      System.out.println(DIVIDER);
    }

    public void showGreetingMessage() {
        System.out.println(DIVIDER);
        System.out.println("Hi! I'm Luke a.k.a your customized chatbot.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public void showGoodbyeMessage() {
        System.out.println("It's my pleasure to help you. Hope to see you again soon! :')");
    }

    public void showMarkTaskAsDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
    }

    public static void showMarkTaskAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
    }

    public static void showTaskAdded(ArrayList<Task> tasks) {
        System.out.println("Yay! I've added this task for you: ");
        // -1 because the taskCount has incremented after adding a new task
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void showTaskDeleted(ArrayList<Task> tasks, Task deletedTask) {
        System.out.println("There you go. I've removed this task for you:");
        System.out.println("  " + deletedTask.toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

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

    public void showError(String output) {
        System.out.println(output);
    }
}
