import java.util.Scanner;

public class Luke {
    private static final String DIVIDER = "——————————————————————————————————————————————————————";

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

    public static void main(String[] args) {
        greeting();
        //String[] tasks = new String[100];
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(DIVIDER);
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon()+ "] " + tasks[i].description);
                }
                System.out.println(DIVIDER);
            } else if (input.startsWith("mark")) {
                int taskIndex = Integer.parseInt(input.substring(5).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println(DIVIDER);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("[" + tasks[taskIndex].getStatusIcon()+ "] " + tasks[taskIndex].description);
                    System.out.println(DIVIDER);
                }
            } else if (input.startsWith("unmark ")) {
                int taskIndex = Integer.parseInt(input.substring(7).trim()) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println(DIVIDER);
                    System.out.println("Nice! I've marked this task as not done yet:");
                    System.out.println("[" + tasks[taskIndex].getStatusIcon()+ "] "+ tasks[taskIndex].description);
                    System.out.println(DIVIDER);
                }
            } else {
                    tasks[taskCount] = new Task(input);
                    System.out.println(DIVIDER);
                    System.out.println("added: " + tasks[taskCount].description);
                    System.out.println(DIVIDER);
                    taskCount += 1;
            }
            input = in.nextLine();

        }
        bye();

    }
}
