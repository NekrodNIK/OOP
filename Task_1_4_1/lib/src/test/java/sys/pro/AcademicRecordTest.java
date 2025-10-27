package sys.pro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** AcademicRecordTest. */
class AcademicRecordTest {
    @Test
    void testIsExam() {
        AcademicRecord exam = new Exam("Диффуры", Grade.EXCELLENT);
        AcademicRecord diff = new DifferentialRecord("Диффуры", Grade.EXCELLENT);
        assertTrue(exam.isExam());
        assertFalse(diff.isExam());
    }

    @Test
    void testGetSubject() {
        AcademicRecord r1 = new Exam("Диффуры", Grade.EXCELLENT);
        AcademicRecord r2 = new DifferentialRecord("Функан", Grade.EXCELLENT);
        assertEquals("Диффуры", r1.getSubject());
        assertEquals("Функан", r2.getSubject());
    }
}
