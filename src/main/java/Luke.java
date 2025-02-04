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
        String[] tasks = new String[100];
        int taskCount = 0;

        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println(DIVIDER);
                for (int i = 0; i < taskCount; i += 1) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(DIVIDER);
            } else {
                tasks[taskCount] = input;
                System.out.println(DIVIDER);
                System.out.println("added: " + tasks[taskCount]);
                System.out.println(DIVIDER);
                taskCount += 1;
            }
            input = in.nextLine();
        }

        bye();

    }
}
