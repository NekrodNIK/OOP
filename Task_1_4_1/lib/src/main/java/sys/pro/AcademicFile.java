package sys.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AcademicFile {
  private ArrayList<Semester> semesters;
  private Grade qualificationWorkGrade;
  private boolean budget;

  public AcademicFile() {
    this.semesters = new ArrayList<Semester>();
    this.qualificationWorkGrade = null;
    this.budget = false;
  }

  public void addSemester(Semester semester) {
    semesters.add(semester);
  }

  public void setQualificationWorkGrade(Grade grade) {
    qualificationWorkGrade = grade;
  }

  public void switchToBudget() {
    this.budget = true;
  }

  public void switchToPaid() {
    this.budget = false;
  }

  public boolean isBudget() {
    return budget;
  }

  public double getAverage() {
    if (semesters.isEmpty()) {
      return 0.0;
    }
    int sum = semesters.stream().map((s) -> s.size()).reduce(0, (x, y) -> x + y);
    int size = semesters.stream().map((s) -> s.size()).reduce(0, (x, y) -> x + y);
    return (double) sum / size;
  }

  public boolean possibleToSwitchToBudget() {
    if (budget || semesters.size() < 2) {
      return false;
    }
    return !semesters.get(semesters.size() - 1).hasSatisfactoryExams()
        && !semesters.get(semesters.size() - 2).hasSatisfactoryExams();
  }

  public boolean possibleToGetHonorsDiploma() {
    if (qualificationWorkGrade == null || qualificationWorkGrade != Grade.EXCELLENT) {
      return false;
    }

    Map<String, Grade> finalGrades = new HashMap<String, Grade>();
    semesters.forEach(
        (sem) -> sem.getRecords().forEach((record) -> finalGrades.put(record.getSubject(), record.getGrade())));

    if (finalGrades.values().stream().anyMatch((g) -> g == Grade.SATISFACTORY)) {
      return false;
    }

    long excellentCount = finalGrades.values().stream().filter((g) -> g == Grade.EXCELLENT).count();
    double percentage = (double) excellentCount / finalGrades.size() * 100;
    return percentage >= 75.0;
  }

  public boolean possibleToIncreaseScholarship() {
    if (!budget || semesters.isEmpty()) {
      return false;
    }

    Semester cur = semesters.getLast();
    return !cur.hasSatisfactoryExams() && !cur.hasGoodExams();
  }
}
