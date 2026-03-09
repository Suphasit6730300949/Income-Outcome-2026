import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IncomeExpenseRecord {

    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Usage: java IncomeExpenseRecord <type> <amount> <description> -s <filename>");
            System.out.println("Example (Income): java IncomeExpenseRecord -i 20000 Salary -s normal.txt");
            System.out.println("Example (Expense): java IncomeExpenseRecord -e 100 Food -s normal.txt");
            return;
        }

        String typeFlag = args[0];
        String amount = args[1];
        String description = args[2];
        String fileName = args[4];
        String typeLabel = "";
        if (typeFlag.equals("-i")) {
            typeLabel = "[INCOME]";
        } else if (typeFlag.equals("-e")) {
            typeLabel = "[EXPENSE]";
        } else {
            System.out.println("Invalid flag: Use -i for Income or -e for Expense");
            return;
        }

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String record = String.format("%s %s: %s | Description: %s", timestamp, typeLabel, amount, description);

        saveToFile(fileName, record);
    }

    private static void saveToFile(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Successfully recorded to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}