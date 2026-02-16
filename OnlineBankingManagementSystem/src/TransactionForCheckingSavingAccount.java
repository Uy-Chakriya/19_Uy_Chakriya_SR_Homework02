import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class TransactionForCheckingSavingAccount {
    private String[] history = new String[100];
    private int count = 0;
    private final DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void addLog(String type, double amount, String remark) {
        if (count >= history.length) {
            System.out.println("Transaction history is full.");
            return;
        }

        String time = LocalDateTime.now().format(formatter);
        String entry = String.format(
                "[%s] %s: $%.2f | Remark: %s",
                time, type, amount, remark
        );

        history[count++] = entry;
    }

    public void showHistory() {
        System.out.println("\nChecking Account Transaction History ");

        if (count == 0) {
            System.out.println("No transactions yet.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println(history[i]);
            }
        }

        System.out.println("---------------------------------------------------");
    }
}
