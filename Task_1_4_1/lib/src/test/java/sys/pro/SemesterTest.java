package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
    void testSum() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Алгебра", Grade.GOOD));
        assertEquals(9, s.sum());
    }

    @Test
    void testSize() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        s.addRecord(new DifferentialRecord("Алгебра", Grade.GOOD));
        assertEquals(2, s.size());
    }

    @Test
    void testGetRecords() {
        Semester s = new Semester();
        s.addRecord(new Exam("Матанализ", Grade.EXCELLENT));
        assertEquals(1, s.getRecords().count());
    }
}
