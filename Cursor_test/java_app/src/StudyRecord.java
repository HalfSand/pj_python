public class StudyRecord {
    private final String day;
    private final double studyHours;

    public StudyRecord(String day, double studyHours) {
        this.day = day;
        this.studyHours = studyHours;
    }

    public String getDay() {
        return day;
    }

    public double getStudyHours() {
        return studyHours;
    }
}
