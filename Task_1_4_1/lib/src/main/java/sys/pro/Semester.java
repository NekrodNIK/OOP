package sys.pro;

import java.util.ArrayList;
import java.util.stream.Stream;

/** Semester. */
public class Semester {
    private ArrayList<AcademicRecord> records;

    public Semester() {
        records = new ArrayList<AcademicRecord>();
    }

    /** Add Record. */
    public void addRecord(AcademicRecord record) {
        records.add(record);
    }

    /**
     * Get Records.
     *
     * @return records
     */
    public Stream<AcademicRecord> getRecords() {
        return records.stream();
    }

    /**
     * Get GradedAcademicRecord stream
     *
     * @return stream
     */
    public Stream<GradedAcademicRecord> getGraded() {
        return getRecords()
                .filter(record -> record instanceof GradedAcademicRecord)
                .map(record -> ((GradedAcademicRecord) record));
    }

    /**
     * Get Exams stream.
     *
     * @return stream
     */
    public Stream<Exam> getExams() {
        return getRecords().filter(record -> record instanceof Exam).map(record -> (Exam) record);
    }

    /**
     * Get DifferentialRecords stream.
     *
     * @return stream
     */
    public Stream<DifferentialRecord> getDifferentialRecords() {
        return getRecords()
                .filter(record -> record instanceof DifferentialRecord)
                .map(record -> (DifferentialRecord) record);
    }

    /**
     * Check if there is a satisfactory grade this semester.
     *
     * @return result
     */
    public boolean hasSatisfactoryExams() {
        return getExams().anyMatch(exam -> exam.getGrade() == Grade.SATISFACTORY);
    }

    /**
     * Check if there is a good grade this semester.
     *
     * @return result
     */
    public boolean hasGoodExams() {
        return getExams().anyMatch(exam -> exam.getGrade() == Grade.GOOD);
    }

    /**
     * Calculate sum of Grades.
     *
     * @return sum
     */
    public int sumGrades() {
        return getGraded().map((g) -> g.getGrade().getValue()).reduce(0, (x, y) -> x + y);
    }

    /**
     * Calculate count of Grades.
     *
     * @return count
     */
    public long countGrades() {
        return getGraded().map((g) -> g.getGrade().getValue()).count();
    }
}
