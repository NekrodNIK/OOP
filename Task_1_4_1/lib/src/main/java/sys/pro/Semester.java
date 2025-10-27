package sys.pro;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Semester {
    private ArrayList<AcademicRecord> records;

    public Semester() {
        records = new ArrayList<AcademicRecord>();
    }

    public void addRecord(AcademicRecord record) {
        records.add(record);
    }

    public Stream<AcademicRecord> getRecords() {
        return records.stream();
    }

    public Stream<Exam> getExams() {
        return getRecords().filter(record -> record instanceof Exam).map(record -> (Exam) record);
    }

    public Stream<DifferentialRecord> getDifferentialRecords() {
        return getRecords()
                .filter(record -> record instanceof DifferentialRecord)
                .map(record -> (DifferentialRecord) record);
    }

    public boolean hasSatisfactoryExams() {
        return getExams().anyMatch(exam -> exam.getGrade() == Grade.SATISFACTORY);
    }

    public boolean hasGoodExams() {
        return getExams().anyMatch(exam -> exam.getGrade() == Grade.GOOD);
    }

    public int sum() {
        return getRecords().map((r) -> r.getGrade().getValue()).reduce(0, (x, y) -> x + y);
    }

    public int size() {
        return records.size();
    }
}
