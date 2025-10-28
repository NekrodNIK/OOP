package sys.pro;

/** AcademicRecord. */
public abstract class AcademicRecord {
    protected String subject;

    /**
     * Constructor.
     *
     * @param subject subject name
     */
    public AcademicRecord(String subject) {
        this.subject = subject;
    }

    /**
     * Get subject name.
     *
     * @return result
     */
    public String getSubject() {
        return subject;
    }
}
