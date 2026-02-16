import java.util.Scanner;
import java.util.regex.Pattern;

public class Validation {
    private static final Scanner sc = new Scanner(System.in);

    // Validates menu options and integer choices
    public static int validateIntInput(int min, int max) {
        while (true) {
            try {
                System.out.print("=> Choose option (" + min + "-" + max + "): ");
                int input = Integer.parseInt(sc.nextLine());
                if (input >= min && input <= max) return input;
                System.out.println("Error: Please enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Please enter a valid integer.");
            }
        }
    }

    // Validates money amounts for deposits and withdrawals
    public static double validateDoubleInput(double min, double max) {
        while (true) {
            try {
                System.out.print("Enter " + label + ": $");
                double input = Double.parseDouble(sc.nextLine());
                if (input >= min && input <= max) return input;
                System.out.printf("Error: Amount must be between $%.2f and $%.2f.\n", min, max);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid amount! Please enter a numeric value.");
            }
        }
    }

    // Validates specific patterns like Name, DOB, and Phone
    public static String validateStringPattern(String regex, String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (Pattern.matches(regex, input)) return input;
            System.out.println("Error: " + errorMsg);
        }
    }
}