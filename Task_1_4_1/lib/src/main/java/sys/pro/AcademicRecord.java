package sys.pro;

/** AcademicRecord */
public abstract class AcademicRecord {
    protected String subject;
    protected Grade grade;

    /**
     * Constructor.
     *
     * @param subject
     * @param grade
     */
    public AcademicRecord(String subject, Grade grade) {
        this.subject = subject;
        this.grade = grade;
    }

    /**
     * Get grade.
     *
     * @return result
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Get subject name.
     *
     * @return result
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Get is exam.
     *
     * @return result
     */
    public abstract boolean isExam();
}
