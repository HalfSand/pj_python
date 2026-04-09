import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    static double calculateAverageFromCsv(String csvPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        String line;
        boolean isHeader = true;
        double sum = 0.0;
        int count = 0;
        int rowNumber = 0; // CSV行号（含表头）

        while ((line = br.readLine()) != null) {
            rowNumber++;

            if (isHeader) {
                isHeader = false;
                continue;
            }

            if (line.trim().isEmpty()) {
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 2) {
                br.close();
                throw new IllegalArgumentException("CSV format error at row " + rowNumber);
            }

            try {
                double hours = Double.parseDouble(parts[1].trim());
                sum += hours;
                count++;
            } catch (NumberFormatException e) {
                br.close();
                throw new IllegalArgumentException("Invalid numeric value at row " + rowNumber + ": " + parts[1]);
            }
        }

        br.close();

        if (count == 0) {
            return 0.0;
        }
        return sum / count;
    }

    public static void main(String[] args) {
        String csvPath;

        if (args.length > 0) {
            csvPath = args[0];
        } else {
            csvPath = "..\\python_app\\data\\study_hours.csv";
        }

        try {
            double avg = calculateAverageFromCsv(csvPath);
            System.out.println("Input CSV: " + csvPath);
            System.out.println("Average study hours from CSV: " + avg);

            if (avg >= 2.0) {
                System.out.println("Level: Good");
            } else {
                System.out.println("Level: Keep Improving");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] File read error: " + e.getMessage());
            System.exit(1);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] " + e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.out.println("[UNEXPECTED ERROR] " + e.getMessage());
            System.exit(1);
        }
    }
}