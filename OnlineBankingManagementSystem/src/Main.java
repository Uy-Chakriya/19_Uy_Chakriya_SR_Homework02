import java.util.Scanner;
public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    private static CheckingAccount checking = null;
    private static SavingAccount saving = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            displayMainMenu();
            choice = Validation.validateIntInput(1, 7);
            switch (choice) {
                case 1 -> createAccount();
                case 2 -> depositMoney();
                case 3 -> withdrawMoney();
                case 4 -> transferMoney();
                case 5 -> displayInfo();
                case 6 -> deleteAccount();
                case 7 -> System.out.println("Transferred all balance from Checking account to Savings account.\n" +
                        "The account was deleted successfully!\n");
                default -> System.out.println(ANSI_RED+ "Invalid option! Please try again." +ANSI_RESET);
            }
        } while (choice != 7);
    }

    private static void displayMainMenu() {
        System.out.println(ANSI_BLUE+"\n================== Online Banking System ==================" + ANSI_RESET);
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Transfer Money");
        System.out.println("5. Display Account Information");
        System.out.println("6. Delete Account");
        System.out.println("7. Exit");
        System.out.println("---------------------------------------------------------------------");
        System.out.print("=> Choose option(1-7) : ");
    }

    private static void createAccount() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>   Creating Account   <<<<<<<<<<<<<<<<<<<<");
        System.out.println("1. Checking Account\n2. Saving Account\n3. Back");
        int type = Validation.validateIntInput(1, 3);
        if (type == 3) return;

        System.out.println("\n>>>>>>>>>>>>>>>>>>>>   Account Information   <<<<<<<<<<<<<<<<<<<<");

        // Validate Name: Only letters and spaces
        String name = Validation.validateStringPattern("^[a-zA-Z\\s]+$",
                "Enter username: ", "Name can only contain letters and spaces.");

        // Validate DOB: dd-mm-yyyy format
        String dob = Validation.validateStringPattern("\\d{2}-\\d{2}-\\d{4}",
                "Enter date of birth (dd-mm-yyyy): ", "Format must be dd-mm-yyyy.");

        // Validate Gender
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();

        // Validate Phone Number: 8 to 10 digits
        String phone = Validation.validateStringPattern("\\d{8,10}",
                "Enter phone number: ", "Phone must be 8 to 10 digits.");

        // Generate random 9-digit account number
        String id = String.valueOf(new java.util.Random().nextInt(900000000) + 100000000);

        if (type == 1) {
            checking = new CheckingAccount(id, name, dob, gender, phone);
            System.out.println("Checking account created successfully!");
        } else {
            saving = new SavingAccount(id, name, dob, gender, phone);
            System.out.println("Saving account created successfully!");
        }
    }

    private static void depositMoney() {
        System.out.println(ANSI_BLUE+ "\n>>>>>>>>>>>>>>>>>>>>   Deposit Money   <<<<<<<<<<<<<<<<<<<<" + ANSI_RESET);
        System.out.println("1. Checking Account\n2. Savings Account\n3. Back");
        System.out.print("Choose an option: ");

        int choice = Validation.validateIntInput(1, 3);
        if (choice == 3)
            return;

        System.out.print("Enter money to deposit: ");
        double amount = Validation.validateDoubleInput(0.01, 1000000);

        if (choice == 1 && checking != null) {
            checking.deposit(amount);
            displayHeader("Checking Account", amount, checking.getBalance());
        } else if (choice == 2 && saving != null) {
            saving.deposit(amount);
            displayHeader("Saving Account", amount, saving.getBalance());
        } else {
            System.out.println(ANSI_RED+"Error: Account not found! Please create an account first."+ ANSI_RESET);
        }
    }

    private static void withdrawMoney() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>   Withdraw Money   <<<<<<<<<<<<<<<<<<<<");

        System.out.println("1. Checking Account\n2. Saving Account\n3. Back");
        System.out.print("Choose an option: ");
        int choice = Validation.validateIntInput(1, 3);
        if (choice == 3) return;
        System.out.print("Enter money to withdraw: ");
        double amount = Validation.validateDoubleInput(0.01, 1000000);

        if (choice == 1 && checking != null) {
            if (checking.getBalance() >= amount) {
                checking.withdraw(amount);
                displayWithdraw(amount, checking.getBalance());
            }
            else System.out.println(ANSI_RED+"Error: Insufficient balance in Checking."+ ANSI_RESET);
        }

        else if (choice == 2 && saving != null)
        {
            if (saving.getBalance() >= amount) {
                saving.withdraw(amount);
                displayWithdraw(amount, saving.getBalance());
            }
            else System.out.println(ANSI_RED+ "Error: Insufficient balance in Saving." + ANSI_RESET);
        }
        else
        {
            System.out.println(ANSI_RED+"Error: Account not found!" + ANSI_RESET);
        }
    }

    private static void transferMoney() {
        if (checking== null || saving ==null) {
            System.out.println(ANSI_RED+"Error==>> You need 2 account to to be able to transfer"+ ANSI_RESET) ;
            return;
        }

        System.out.println("\n>>>>>>>>>>>>>>>>>>>>   Transfer Money   <<<<<<<<<<<<<<<<<<<<");
        System.out.println( ANSI_GREEN+"1. Checking Account -> Saving Account" + ANSI_RESET);
        System.out.println(ANSI_GREEN+"2. Saving Account    -> Checking Account" +ANSI_RESET);
        System.out.println(ANSI_GREEN+"3. Back" + ANSI_RESET);
        System.out.println("\n==============================================================");

        System.out.println("Choose an option: ");
        int choice = Validation.validateIntInput(1, 3);
        if (choice == 3) return;
        System.out.println("\n-----------------------------------------------------------");
        System.out.println("|Transfer From Checking Account -> Saving Account|");
        System.out.println("\n-----------------------------------------------------------");


        System.out.print("====> Input Transfer Amount : $");
        double amount = Validation.validateDoubleInput(0.01,
                        checking.getBalance()
                        + saving.getBalance());

        if (choice == 1) {
            if (checking.getBalance() >= amount) {
                checking.transfer(amount, saving);
                System.out.println("Transfer successfully!");
            }
            else System.out.println("==>> Error: Insufficient balance.");
        }
        else
        {
            if (saving.getBalance() >= amount) {
                saving.transfer(amount, checking);
                System.out.println("Transfer successfully!");
            }
            else System.out.println("==>> Error: Insufficient balance.");
        }
    }

    private static void deleteAccount() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>>   Delete Account   <<<<<<<<<<<<<<<<<<<<" +ANSI_RESET);
        System.out.println("1. Checking Account\n2. Saving Account\n3. Back");
        int choice = Validation.validateIntInput(1, 3);
        if (choice == 3) return;

        System.out.print("Are you sure you want to delete this account? (Y/N): ");
        String confirm = sc.next();

        if (confirm.equalsIgnoreCase("y")) {
            if (choice == 1 && checking != null) {
                if (saving != null) checking.transfer(checking.getBalance(), saving);
                checking = null;
                System.out.println("Checking account deleted successfully!");
            } else if (choice == 2 && saving != null) {
                if (checking != null) saving.transfer(saving.getBalance(), checking);
                saving = null;
                System.out.println("Saving account deleted successfully!");
            } else System.out.println("Account does not exist.");
        }
    }

    private static void displayWithdraw(double wit, double bal) {
        System.out.println("Withdraw:                 $ " + wit);
        System.out.println("Total balance:            $ " + bal);
        System.out.println(ANSI_RED+ "\nWithdrew successfully!" + ANSI_RESET);
    }

    private static void displayInfo() {
        if(checking != null) checking.displayAccountInfo();
        if(saving != null) saving.displayAccountInfo();
        if(checking == null && saving == null)
            System.out.println("No accounts found.");
    }

    private static void displayHeader(String type, double recieved, double total) {
        System.out.println("\n                " + type + "                ");
        System.out.println("Received    :              $ " +  recieved);
        System.out.println("Total Amount:                $ " + total);
        System.out.println("==============================================================");
    }
}
