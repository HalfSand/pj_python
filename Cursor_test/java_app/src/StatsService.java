import java.util.List;

public class StatsService {

    public double calculateAverage(List<StudyRecord> records) {
        if (records.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (StudyRecord record : records) {
            sum += record.getStudyHours();
        }

        return sum / records.size();
    }

    public double findMax(List<StudyRecord> records) {
        if (records.isEmpty()) {
            return 0.0;
        }

        double max = records.get(0).getStudyHours();
        for (StudyRecord record : records) {
            if (record.getStudyHours() > max) {
                max = record.getStudyHours();
            }
        }

        return max;
    }

    public double findMin(List<StudyRecord> records) {
        if (records.isEmpty()) {
            return 0.0;
        }

        double min = records.get(0).getStudyHours();
        for (StudyRecord record : records) {
            if (record.getStudyHours() < min) {
                min = record.getStudyHours();
            }
        }

        return min;
    }
}
