package sys.pro;

public class DifferentialCredit extends AcademicRecord {
  public DifferentialCredit(String subject, Grade grade) {
    super(subject, grade);
  }

  @Override
  public boolean isExam() {
    return false;
  }
}
