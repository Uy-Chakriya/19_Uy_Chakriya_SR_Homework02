public class SavingAccount implements Account {
    private String accountNumber;
    private String userName;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private double balance;
    private final double rate = 0.05;

    public SavingAccount(String accountNumber,
                         String userName,
                         String dateOfBirth,
                         String gender,
                         String phoneNumber)
    {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.balance = 0.0;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            this.balance -= amount;
        }
    }

    @Override
    public void transfer(double amount, Account targetAccount) {
        if (amount> 0 && amount <=balance) {
            this.balance -= amount;
            targetAccount.deposit(amount);}
    }
    @Override
    public void displayAccountInfo() {
        System.out.println("\n>>>>>>>>>>>>>>>>>>>> Savings Account <<<<<<<<<<<<<<<<<<<<");
        System.out.println("Account Type: Savings Account");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("User Name: " + userName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Balance: " + balance + " $");
        System.out.println("Interest Rate: " + (rate * 100) + "%");
        System.out.println("=============================================================");
    }

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}