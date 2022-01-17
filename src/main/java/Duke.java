import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello I'm Duke\nWhat can I do for you today?");
        boolean loop = true;
        Scanner sc = new Scanner(System.in);
        
        while (loop) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                loop = false;
                System.out.println("same");
            } else {
                System.out.println(command);
            }

        }
        System.out.println("Bye, see you next time");


    }
}
