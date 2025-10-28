package sys.pro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/** AcademicFile. */
public class AcademicFile {
    private ArrayList<Semester> semesters;
    private Grade qualificationWorkGrade;
    private boolean budget;

    /** Constructor. */
    public AcademicFile() {
        this.semesters = new ArrayList<Semester>();
        this.qualificationWorkGrade = null;
        this.budget = false;
    }

    /**
     * Add semester.
     *
     * @param semester Semester
     */
    public void addSemester(Semester semester) {
        semesters.add(semester);
    }

    /**
     * Set qualification work grade.
     *
     * @param grade Grade
     */
    public void setQualificationWorkGrade(Grade grade) {
        qualificationWorkGrade = grade;
    }

    /** Switch to budget form. */
    public void switchToBudget() {
        this.budget = true;
    }

    /** Switch to paid form. */
    public void switchToPaid() {
        this.budget = false;
    }

    /**
     * Check if current form is a budget.
     *
     * @return result
     */
    public boolean isBudget() {
        return budget;
    }

    /**
     * Get average grade.
     *
     * @return result
     */
    public double getAverage() {
        if (semesters.isEmpty()) {
            return 0.0;
        }
        int sum = semesters.stream().map((s) -> s.sumGrades()).reduce(0, (x, y) -> x + y);
        long size =
                semesters.stream().map((s) -> s.countGrades()).reduce((long) 0, (x, y) -> x + y);
        return (double) sum / size;
    }

    /**
     * Check if it's possible to switch to a budget form.
     *
     * @return result
     */
    public boolean possibleToSwitchToBudget() {
        if (budget || semesters.size() < 2) {
            return false;
        }
        return !semesters.get(semesters.size() - 1).hasSatisfactoryExams()
                && !semesters.get(semesters.size() - 2).hasSatisfactoryExams();
    }

    /**
     * Check if it's possible to get diploma with honors.
     *
     * @return result
     */
    public boolean possibleToGetHonorsDiploma() {
        if (qualificationWorkGrade == null) {
            return true;
        }

        if (qualificationWorkGrade != Grade.EXCELLENT) {
            return false;
        }

        Map<String, Grade> finalGrades = new HashMap<String, Grade>();
        semesters.forEach(
                (sem) ->
                        sem.getGraded()
                                .forEach(
                                        (record) ->
                                                finalGrades.put(
                                                        record.getSubject(), record.getGrade())));

        if (finalGrades.values().stream().anyMatch((g) -> g == Grade.SATISFACTORY)) {
            return false;
        }

        long excellentCount =
                finalGrades.values().stream().filter((g) -> g == Grade.EXCELLENT).count();
        double percentage = (double) excellentCount / finalGrades.size() * 100;
        return percentage >= 75.0;
    }

    /**
     * Check if it's possible to increase the scholarship.
     *
     * @return result
     */
    public boolean possibleToIncreaseScholarship() {
        if (!budget || semesters.isEmpty()) {
            return false;
        }

        Semester cur = semesters.getLast();
        return !cur.hasSatisfactoryExams() && !cur.hasGoodExams();
    }
}
