package sys.pro;

public class Exam extends AcademicRecord {
    public Exam(String subject, Grade grade) {
        super(subject, grade);
    }

    @Override
    public boolean isExam() {
        return true;
    }
}
