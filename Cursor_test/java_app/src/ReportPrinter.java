import java.util.List;

public class ReportPrinter {

    public void printReport(String csvPath, List<StudyRecord> records, double avg, double max, double min) {
        System.out.println("Input CSV: " + csvPath);
        System.out.println("Records count: " + records.size());
        System.out.println("Average study hours: " + avg);
        System.out.println("Max study hours: " + max);
        System.out.println("Min study hours: " + min);

        if (avg >= 2.0) {
            System.out.println("Level: Good");
        } else {
            System.out.println("Level: Keep Improving");
        }
    }
}
