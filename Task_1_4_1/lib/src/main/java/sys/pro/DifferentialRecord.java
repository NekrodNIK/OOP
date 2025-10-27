package sys.pro;

/** DifferentialRecord. */
public class DifferentialRecord extends AcademicRecord {
    public DifferentialRecord(String subject, Grade grade) {
        super(subject, grade);
    }

    @Override
    public boolean isExam() {
        return false;
    }
}
