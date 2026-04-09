import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public List<StudyRecord> readStudyRecords(String csvPath) throws IOException {
        List<StudyRecord> records = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(csvPath));
        String line;
        boolean isHeader = true;
        int rowNumber = 0;

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

            String day = parts[0].trim();
            try {
                double hours = Double.parseDouble(parts[1].trim());
                records.add(new StudyRecord(day, hours));
            } catch (NumberFormatException e) {
                br.close();
                throw new IllegalArgumentException(
                    "Invalid numeric value at row " + rowNumber + ": " + parts[1]
                );
            }
        }

        br.close();
        return records;
    }
}
