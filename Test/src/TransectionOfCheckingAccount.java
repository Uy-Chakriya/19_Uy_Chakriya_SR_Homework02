import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TransectionOfCheckingAccount {
    private List<String> history = new ArrayList<>();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void addLog(String type, double amount, String remark) {
        String time = LocalDateTime.now().format(formatter);
        String entry = String.format("[%s] %s: $%.2f | Remark: %s", time, type, amount, remark);
        history.add(entry);
    }

    public void showHistory() {
        System.out.println("\n--- Checking Account Transaction History ---");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            history.forEach(System.out::println);
        }
        System.out.println("--------------------------------------------");
    }
}