import java.util.Arrays;
import java.util.Scanner;

public class Luke {
    private static final String DIVIDER = "——————————————————————————————————————————————————————————————————";

    public static void greeting() {
        System.out.println(DIVIDER);
        System.out.println("Hi! I'm Luke a.k.a your customized chatbot.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void bye() {
        System.out.println(DIVIDER);
        System.out.println("It's my pleasure to help you. Hope to see you again soon! :')");
        System.out.println(DIVIDER);
    }

    private static void markTaskAsDone(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks[taskIndex].toString());
        System.out.println(DIVIDER);
    }

    private static void markTaskAsNotDone(Task[] tasks, int taskIndex) {
        tasks[taskIndex].markAsNotDone();
        System.out.println(DIVIDER);
        System.out.println("Nice! I've marked this task as not done yet:");
        System.out.println(tasks[taskIndex].toString());
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

    private static void printTaskList(Task[] tasks) {
        System.out.println(DIVIDER);
        if (Task.taskCount == 0) {
            System.out.println("You don't have any tasks yet. Time to add one now!");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.taskCount; i += 1) {
                System.out.println((i + 1) + "." + tasks[i].toString());
            }
        }
        System.out.println(DIVIDER);
    }

    private static void messageAfterAddingTask(Task[] tasks) {
        System.out.println(DIVIDER);
        System.out.println("Yay! I've added this task for you: ");
        // -1 because the taskCount has incremented after adding a new task
        System.out.println("  " + tasks[Task.taskCount - 1].toString());
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    private static void processTasks(String input, Task[] tasks) throws IndexOutOfBoundsException, TaskLimitExceededException,
            EmptyTaskDescriptionException, InvalidCommandException {

        if (input.equals("list")) {
            printTaskList(tasks);

        } else if (input.startsWith("mark")) {
            // -1 because the first task starts with index 0
            int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;

            if (taskIndex < 0 || taskIndex > Task.taskCount) {
                throw new IndexOutOfBoundsException();
            }

            markTaskAsDone(tasks, taskIndex);

        } else if (input.startsWith("unmark")) {
            int taskIndex = Integer.parseInt(input.substring(6).trim()) - 1;

            if (taskIndex < 0 || taskIndex > Task.taskCount) {
                throw new IndexOutOfBoundsException();
            }

            markTaskAsNotDone(tasks, taskIndex);

        } else if (input.startsWith("deadline")) {
            if (Task.taskCount >= 100) { // Check if max limit is reached
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

            tasks[Task.taskCount] = new Deadline(taskDescription, deadline);
            messageAfterAddingTask(tasks);

        } else if (input.startsWith("event")) {
            if (Task.taskCount >= 100) { // Check if max limit is reached
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

            tasks[Task.taskCount] = new Event(taskDescription, startDate, endDate);
            messageAfterAddingTask(tasks);

        } else if (input.startsWith("todo")) {
            if (Task.taskCount >= 100) { // Check if max limit is reached
                throw new TaskLimitExceededException();
            }

            String todoDescription = input.substring(4).trim();

            if (todoDescription.isEmpty()) {
                throw new EmptyTaskDescriptionException();
            }

            tasks[Task.taskCount] = new Todo(todoDescription);
            messageAfterAddingTask(tasks);

        } else {
            throw new InvalidCommandException();
        }
    }


    public static void main(String[] args) {

        greeting();

        Task[] tasks = new Task[100];
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            try {
                processTasks(input, tasks);
            } catch (EmptyTaskDescriptionException | InvalidCommandException e) {
                System.out.println(e.getMessage());
                System.out.println(DIVIDER);
            } catch (IllegalArgumentException e) {
                System.out.println("Whoops, I think your description is not complete.");
                System.out.println("It could also be your wrong input format. Please try again.");
                System.out.println(DIVIDER);
            } catch (IndexOutOfBoundsException | NullPointerException e) {
                System.out.println("Hmm... Why are you trying to mark/unmark a nonexistent task?");
                System.out.println("I think you either don't have any tasks or mark the wrong one.");
                System.out.println(DIVIDER);
            } catch (TaskLimitExceededException e) {
                System.out.println(e.getMessage());
            }
            input = in.nextLine();
        }
        bye();
    }

}
