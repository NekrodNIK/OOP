package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

/** SemesterTest. */
class SemesterTest {
    @Test
    void testGetDifferentialRecords() {
        Semester s = new Semester();
        s.addRecord(new DifferentialRecord("ДМТА", Grade.EXCELLENT));
        s.addRecord(new Exam("Матанализ", Grade.GOOD));
        assertEquals(1, s.getDifferentialRecords().count());
    }

    @Test
    void testSumGrades() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Алгебра", Grade.GOOD));
        assertEquals(9, s.sumGrades());
    }

    @Test
    void testCountGrades() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Алгебра", Grade.GOOD));
        assertEquals(2, s.countGrades());
    }

    @Test
    void testGetRecords() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        assertEquals(1, s.getRecords().count());
    }

    @Test
    void testGetGraded() {
        Semester s = new Semester();
        Exam r1;
        DifferentialRecord r2;
        s.addRecord(r1 = new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(r2 = new DifferentialRecord("Алгебра", Grade.SATISFACTORY));
        s.addRecord(new SimpleRecord("История России"));
        assertEquals(Arrays.asList(r1, r2), s.getGraded().toList());
    }
}
