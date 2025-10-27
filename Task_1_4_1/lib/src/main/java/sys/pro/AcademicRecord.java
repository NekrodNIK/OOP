package sys.pro;

public abstract class AcademicRecord {
    protected String subject;
    protected Grade grade;

    public AcademicRecord(String subject, Grade grade) {
        this.subject = subject;
        this.grade = grade;
    }
    
    public Grade getGrade() {
        return grade;
    }
    
    public String getSubject() {
        return subject;
    }

    public abstract boolean isExam();
}
