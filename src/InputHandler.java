import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    public int readInt(String message) {

        while (true) {

            try {
                System.out.print(message);

                int value = scanner.nextInt();
                scanner.nextLine();

                return value;

            } catch (InputMismatchException e) {

                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    public String readString(String message) {

        while (true) {

            System.out.print(message);

            String value = scanner.nextLine();

            if (!value.isBlank()) {
                return value;
            }

            System.out.println("Input cannot be empty.");
        }
    }
}