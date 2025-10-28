package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** AcademicFileTest. */
class AcademicFileTest {
    @Test
    void basicInitialState() {
        final AcademicFile af = new AcademicFile();
        assertFalse(af.isBudget());
        assertEquals(0.0, af.getAverage());
    }

    @Test
    void switchToBudgetWorks() {
        final AcademicFile af = new AcademicFile();
        af.switchToBudget();
        assertTrue(af.isBudget());
    }

    @Test
    void cantSwitchWhenAlreadyBudget() {
        final AcademicFile af = new AcademicFile();
        af.switchToBudget();
        assertFalse(af.possibleToSwitchToBudget());
    }

    @Test
    void switchToBudgetWithGoodGrades() {
        final AcademicFile af = new AcademicFile();
        Semester s1 = new Semester();
        Semester s2 = new Semester();
        s1.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s2.addRecord(new Exam("Алгебра", Grade.GOOD));
        af.addSemester(s1);
        af.addSemester(s2);
        assertTrue(af.possibleToSwitchToBudget());
    }

    @Test
    void cantSwitchWithBadGrades() {
        final AcademicFile af = new AcademicFile();
        Semester s1 = new Semester();
        Semester s2 = new Semester();
        s1.addRecord(new Exam("Матанализ", Grade.SATISFACTORY));
        s2.addRecord(new Exam("Алгебра", Grade.GOOD));
        af.addSemester(s1);
        af.addSemester(s2);
        assertFalse(af.possibleToSwitchToBudget());
    }

    @Test
    void honorsDiplomaBasicCase() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new Exam("Алгебра", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("ДМТА", Grade.EXCELLENT));
        af.addSemester(s);
        af.setQualificationWorkGrade(Grade.EXCELLENT);
        assertTrue(af.possibleToGetHonorsDiploma());
    }

    @Test
    void noHonorsWithBadQualification() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        af.addSemester(s);
        af.setQualificationWorkGrade(Grade.GOOD);
        assertFalse(af.possibleToGetHonorsDiploma());
    }

    @Test
    void noHonorsWithSatisfactory() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.SATISFACTORY));
        af.addSemester(s);
        af.setQualificationWorkGrade(Grade.EXCELLENT);
        assertFalse(af.possibleToGetHonorsDiploma());
    }

    @Test
    void scholarshipForExcellentStudent() {
        final AcademicFile af = new AcademicFile();
        af.switchToBudget();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Ангеом", Grade.EXCELLENT));
        af.addSemester(s);
        assertTrue(af.possibleToIncreaseScholarship());
    }

    @Test
    void noScholarshipWithGoodExams() {
        final AcademicFile af = new AcademicFile();
        af.switchToBudget();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.GOOD));
        af.addSemester(s);
        assertFalse(af.possibleToIncreaseScholarship());
    }

    @Test
    void mixedGradesForHonors() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new Exam("Алгебра", Grade.EXCELLENT));
        s.addRecord(new Exam("Ангеом", Grade.GOOD));
        s.addRecord(new DifferentialRecord("ДМТА", Grade.GOOD));
        af.addSemester(s);
        af.setQualificationWorkGrade(Grade.EXCELLENT);
        assertFalse(af.possibleToGetHonorsDiploma());
    }

    @Test
    void differentialGoodDoesntAffectScholarship() {
        final AcademicFile af = new AcademicFile();
        af.switchToBudget();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Алгебра", Grade.GOOD));
        af.addSemester(s);
        assertTrue(af.possibleToIncreaseScholarship());
    }

    @Test
    void lastGradeCountsForHonors() {
        final AcademicFile af = new AcademicFile();
        Semester s1 = new Semester();
        Semester s2 = new Semester();
        s1.addRecord(new Exam("Матанализ", Grade.SATISFACTORY));
        s2.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        af.addSemester(s1);
        af.addSemester(s2);
        af.setQualificationWorkGrade(Grade.EXCELLENT);
        assertTrue(af.possibleToGetHonorsDiploma());
    }

    @Test
    void noScholarshipIfNotBudget() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        af.addSemester(s);
        assertFalse(af.possibleToIncreaseScholarship());
    }

    @Test
    void getAverage() {
        final AcademicFile af = new AcademicFile();
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new Exam("Ангеом", Grade.GOOD));
        s.addRecord(new Exam("ДМТА", Grade.GOOD));
        s.addRecord(new Exam("Алгебра", Grade.GOOD));
        af.addSemester(s);
        assertEquals(4.25, af.getAverage());
    }
}
