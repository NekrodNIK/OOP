package sys.pro;

/** GradedAcademicRecord. */
public abstract class GradedAcademicRecord extends AcademicRecord {
    protected Grade grade;

    /**
     * Constructor.
     *
     * @param subject subject name
     * @param grade grade
     */
    public GradedAcademicRecord(String subject, Grade grade) {
        super(subject);
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
}
