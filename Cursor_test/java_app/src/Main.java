import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String csvPath;
        if (args.length > 0) {
            csvPath = args[0];
        } else {
            csvPath = "..\\python_app\\data\\study_hours.csv";
        }

        CsvReader csvReader = new CsvReader();
        StatsService statsService = new StatsService();
        ReportPrinter reportPrinter = new ReportPrinter();

        try {
            List<StudyRecord> records = csvReader.readStudyRecords(csvPath);

            double avg = statsService.calculateAverage(records);
            double max = statsService.findMax(records);
            double min = statsService.findMin(records);

            reportPrinter.printReport(csvPath, records, avg, max, min);

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
