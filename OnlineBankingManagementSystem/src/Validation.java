import java.util.regex.Pattern;

public class Validation {

    static int validateIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(Main.sc.next());
                if (input >= min && input <= max) return input;
                System.out.print("Please enter a number between " + min + "-" + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Enter a number: ");
            }
        }
    }

    static double validateDoubleInput(double min, double max) {
        while (true) {
            try {
                double input = Double.parseDouble(Main.sc.next());
                if (input >= min && input <= max) return input;
                System.out.print("Please enter an amount between $: " + max);
            } catch (NumberFormatException e) {
                System.out.print("Invalid amount! Enter a numeric value: ");
            }
        }
    }

    static String validateStringPattern(String regex, String messgShow) {
        while (true) {
            String input = Main.sc.nextLine();
            if (Pattern.matches(regex, input)) return input;
            System.out.print("Invalid!  " + messgShow + ": ");
        }
    }
}
