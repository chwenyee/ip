import java.util.Arrays;
import java.util.Scanner;

public class Luke {
    private static final String DIVIDER = "——————————————————————————————————————————————————————————————————";

    public static void greeting () {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void bye () {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void markTaskAsDone (Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static void markTaskAsNotDone (Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsNotDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    public static String filterInput (String input, String keyword, String filterType) {
        String[] words = input.split(" ");
        int filterIndex = -1;
        int indexBetweenFromTo = -1;

        // To find the index of the keyword
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains(keyword)) {
                filterIndex = i + 1 ;
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

        if (filterType.equals("taskDescription") ) {
            return String.join(" ", Arrays.copyOfRange(words, 1, filterIndex - 1));
        } else if (filterType.equals("eventStartDate") ) {
            return String.join(" ", Arrays.copyOfRange(words, filterIndex, indexBetweenFromTo));
        } else {
            return String.join(" ", Arrays.copyOfRange(words, filterIndex, words.length));
        }
    }

    public static void printTaskList (Task[] tasks) {
        System.out.println(DIVIDER);
        if (Task.taskCount == 0) {
            System.out.println("You don't have any tasks. Time to add one now!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.taskCount; i += 1) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
        System.out.println(DIVIDER);
    }

    public static void messageAfterAddingTask (Task[] tasks) {
        System.out.println(DIVIDER);
        System.out.println("Got it. I've added this task: ");
        // -1 because the taskCount has incremented after adding a new task
        System.out.println("  " + tasks[Task.taskCount - 1].toString());
        System.out.println("Now you have " +  Task.taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {

        greeting();

        Task[] tasks = new Task[100];
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printTaskList(tasks);
            } else if (input.startsWith("mark ")) {
                // -1 because the first task starts with index 0
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < Task.taskCount) {
                    markTaskAsDone(tasks, taskIndex);
                }
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < Task.taskCount) {
                    markTaskAsNotDone(tasks, taskIndex);
                }
            } else if (input.startsWith("deadline ")) {
                String taskDescription = filterInput(input, "/by", "taskDescription");
                String deadline = filterInput(input, "/by", "deadline");
                tasks[Task.taskCount] = new Deadline(taskDescription, deadline);
                messageAfterAddingTask(tasks);
            }else if (input.startsWith("event ")) {
                String taskDescription = filterInput(input, "/from", "taskDescription");
                String startDate = filterInput(input, "/from", "eventStartDate");
                String endDate = filterInput(input, "/to", "eventEndDate");
                tasks[Task.taskCount] = new Event(taskDescription, startDate, endDate);
                messageAfterAddingTask(tasks);
            } else {
                String todoDescription = input.substring(5).trim();
                tasks[Task.taskCount] = new Todo(todoDescription);
                messageAfterAddingTask(tasks);
            }
            input = in.nextLine();
        }
        bye();
    }
}
