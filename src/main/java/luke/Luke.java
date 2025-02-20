package luke;

import luke.task.Deadline;
import luke.task.Event;
import luke.task.Task;
import luke.task.Todo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Luke {

    public static final String DIVIDER = "------------------------------------------------------------------";

    private static void greeting() {
        System.out.println(DIVIDER);
        System.out.println("Hi! I'm Luke a.k.a your customized chatbot.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    private static void bye() {
        System.out.println(DIVIDER);
        System.out.println("It's my pleasure to help you. Hope to see you again soon! :')");
        System.out.println(DIVIDER);
    }

    private static void markTaskAsDone(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(DIVIDER);
    }

    private static void markTaskAsNotDone(ArrayList<Task> tasks, int taskIndex) {
        tasks.get(taskIndex).markAsNotDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(tasks.get(taskIndex).toString());
        System.out.println(DIVIDER);
    }

    private static String filterInput(String input, String keyword, String filterType) {
        String[] words = input.split(" ");
        int filterIndex = -1;
        int indexBetweenFromTo = -1;

        // To find the index of the keyword
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(keyword)) {
                filterIndex = i + 1;
                break;
            }
        }

        // To handle the case of text extraction between "/from" and "/to" when the task is Event
        if (filterType.equals("eventStartDate")) {
            for (int i = 0; i < words.length; i++) {
                if (words[i].contains("/to")) {
                    indexBetweenFromTo = i;
                    break;
                }
            }
        }

        if (filterType.equals("taskDescription")) {
            return String.join(" ", Arrays.copyOfRange(words, 1, filterIndex - 1));
        } else if (filterType.equals("eventStartDate")) {
            return String.join(" ", Arrays.copyOfRange(words, filterIndex, indexBetweenFromTo));
        } else {
            return String.join(" ", Arrays.copyOfRange(words, filterIndex, words.length));
        }
    }

    private static void printTaskList(ArrayList<Task> tasks) {
        System.out.println(DIVIDER);
        if (Task.getTaskCount() == 0) {
            System.out.println("You don't have any tasks yet. Time to add one now!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getTaskCount(); i += 1) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
        }
        System.out.println(DIVIDER);
    }

    private static void messageAfterAddingTask(ArrayList<Task> tasks) {
        System.out.println(DIVIDER);
        System.out.println("Yay! I've added this task for you: ");
        // -1 because the taskCount has incremented after adding a new task
        System.out.println("  " + tasks.get(Task.getTaskCount() - 1).toString());
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER);

    }

    private static void messageAfterRemovingTask(Task removedTask) {
        System.out.println(DIVIDER);
        System.out.println("There you go. I've removed this task for you:");
        System.out.println("  " + removedTask.toString());
        Task.setTaskCount(Task.getTaskCount() - 1);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private static void processTasks(String input, ArrayList<Task> tasks) throws IndexOutOfBoundsException, TaskLimitExceededException,
            EmptyTaskDescriptionException, InvalidCommandException {

        if (input.equals("list")) {
            printTaskList(tasks);

        } else if (input.startsWith("mark")) {
            // -1 because the first task starts with index 0
            int taskIndex = Integer.parseInt(input.substring(4).trim()) - 1;

            if (taskIndex < 0 || taskIndex >= Task.getTaskCount()) {
                throw new IndexOutOfBoundsException();
            }

            markTaskAsDone(tasks, taskIndex);

        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;

            if (taskIndex < 0 || taskIndex >= Task.getTaskCount()) {
                throw new IndexOutOfBoundsException();
            }

            markTaskAsNotDone(tasks, taskIndex);

        } else if (input.startsWith("remove")) {
            int taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;

            if (taskIndex < 0 || taskIndex > Task.getTaskCount()) {
                throw new IndexOutOfBoundsException();
            }

            Task removed = tasks.remove(taskIndex);
            messageAfterRemovingTask(removed);

        } else if (input.startsWith("deadline")) {
            if (Task.getTaskCount() >= 100) { // Check if max limit is reached
                throw new TaskLimitExceededException();
            }

            if (input.trim().equals("deadline")) {
                throw new EmptyTaskDescriptionException();
            }

            String taskDescription = filterInput(input, "/by", "taskDescription");
            String deadline = filterInput(input, "/by", "deadline");

            if (taskDescription.isEmpty() || deadline.isEmpty()) {
                throw new EmptyTaskDescriptionException();
            }

            tasks.add(new Deadline(taskDescription, deadline, false));
            messageAfterAddingTask(tasks);

        } else if (input.startsWith("event")) {
            if (Task.getTaskCount() >= 100) { // Check if max limit is reached
                throw new TaskLimitExceededException();
            }

            if (input.trim().equals("event")) {
                throw new EmptyTaskDescriptionException();
            }

            String taskDescription = filterInput(input, "/from", "taskDescription");
            String startDate = filterInput(input, "/from", "eventStartDate");
            String endDate = filterInput(input, "/to", "eventEndDate");

            if (taskDescription.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
                throw new EmptyTaskDescriptionException();
            }

            tasks.add(new Event(taskDescription, startDate, endDate, false));
            messageAfterAddingTask(tasks);

        } else if (input.startsWith("todo")) {
            // Check if max limit is reached
            if (Task.getTaskCount() >= 100) {
                throw new TaskLimitExceededException();
            }

            String todoDescription = input.substring(4).trim();

            if (todoDescription.isEmpty()) {
                throw new EmptyTaskDescriptionException();
            }

            tasks.add(new Todo(todoDescription, false));
            messageAfterAddingTask(tasks);

        } else {
            throw new InvalidCommandException();
        }
    }

    public static void main(String[] args) {

        greeting();

        // To save all existing tasks in the ArrayList tasks
        ArrayList<Task> tasks = Storage.loadTasksFromFile();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        while (!input.equals("bye")) {
            try {
                processTasks(input, tasks);
                // Overwrite luke.txt every time a new task is added
                // This is done by writing all tasks that are in the list into luke.txt
                Storage.saveTasksToFile(tasks);
            } catch (EmptyTaskDescriptionException | InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println(DIVIDER);
            } catch (IllegalArgumentException e) {
                System.out.println("Whoops, I think your description is not complete.");
                System.out.println("It could also be your wrong input format. Please try again.");
                System.out.println(DIVIDER);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Hmm... Why are you trying to do something on a non-existent task?");
                System.out.println("I think you either don't have any tasks or select the wrong one.");
                System.out.println(DIVIDER);
            } catch (TaskLimitExceededException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine();
        }
        bye();
    }
}
