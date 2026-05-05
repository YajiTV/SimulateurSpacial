package services;
import java.util.InputMismatchException;
import java.util.Scanner;

public class InputHelper {
    public static int readInt(Scanner scanner, String message, int min, int max) {
        int value = -1; 
        boolean valid = false;
        
        while (!valid) {
            System.out.println(message);
            try {
                value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    valid = true;
                } else {
                    System.out.println("Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        return value;
    }
}
