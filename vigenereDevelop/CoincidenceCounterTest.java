package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.coincidence;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoincidenceCounterTest {

    private static final double DELTA = 1e-7;
    private final CoincidenceCounter subject = new CoincidenceCounter();

    @Test
    void returns_1_if_string_consists_equal_characters() {
        double actualIndex = subject.countIndexOfCoincidence("AAAA");
        assertEquals(1., actualIndex, DELTA);
    }

    @Test
    void returns_0_if_string_contains_only_different_characters() {
        double actualIndex = subject.countIndexOfCoincidence("runtime");
        assertEquals(0., actualIndex, DELTA);
    }

    @Test
    void is_case_sensitive() {
        double index = subject.countIndexOfCoincidence("AAaa");
        assertEquals(4. / 12, index, DELTA);
    }

    @Test
    void counts_spaces() {
        double index = subject.countIndexOfCoincidence("A B C");
        assertEquals(2. / 20, index, DELTA);
    }

    @Test
    void counts_punctuation() {
        double index = subject.countIndexOfCoincidence("A,B,C. D,E,F.");
        assertEquals(14. / 156, index);
    }

    @Test
    void supports_unicode() {
        double index = subject.countIndexOfCoincidence("вычислитель");
        assertEquals(4. / 110, index, DELTA);
    }
}
