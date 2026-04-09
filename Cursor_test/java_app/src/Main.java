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

        while ((line = br.readLine()) != null) {
            if (isHeader) {
                isHeader = false; // 跳过表头
                continue;
            }

            String[] parts = line.split(",");
            double hours = Double.parseDouble(parts[1]);
            sum += hours;
            count++;
        }

        br.close();

        if (count == 0) {
            return 0.0;
        }
        return sum / count;
    }

    public static void main(String[] args) {
        // 从 java_app 出发，去读取 ../python_app/data/study_hours.csv
        String csvPath = "..\\python_app\\data\\study_hours.csv";

        try {
            double avg = calculateAverageFromCsv(csvPath);
            System.out.println("Average study hours from CSV: " + avg);

            if (avg >= 2.0) {
                System.out.println("Level: Good");
            } else {
                System.out.println("Level: Keep Improving");
            }
        } catch (IOException e) {
            System.out.println("File read error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Data format error: " + e.getMessage());
        }
    }
}