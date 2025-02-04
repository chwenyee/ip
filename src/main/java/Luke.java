import java.util.Scanner;

public class Luke {
    public static void greeting () {
        System.out.println("——————————————————————————————————————————————————————");
        System.out.println("Hello! I'm Luke");
        System.out.println("What can I do for you?");
        System.out.println("——————————————————————————————————————————————————————");
    }

    public static void main(String[] args) {
        greeting();
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();

        while (!input.equals("bye")) {
            System.out.println("——————————————————————————————————————————————————————");
            System.out.println(input);
            System.out.println("——————————————————————————————————————————————————————");
            input = in.nextLine();
        }

        System.out.println("——————————————————————————————————————————————————————");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("——————————————————————————————————————————————————————");
    }
}
