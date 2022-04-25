package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.autocorrelation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AutocorrelationCounterTest {

    public static final double DELTA = 1e-7;
    private final AutocorrelationCounter subject = new AutocorrelationCounter();

    @Test
    void stringShiftTest() {
        String input = "ILoveMarkina";
        String shiftedString = subject.stringShift(input, 4).toString();
        assertEquals("eMarkina", shiftedString);
    }

    @Test
    void removeNonLettersTest() {
        String input = "!abc? fg, hj.7%k";
        String expected = "abcfghjk";
        assertEquals(expected, subject.removeAllNonLetters(input));
    }

    @Test
    void returns_0_if_string_contains_unique_characters() {
        assertEquals(0., subject.computeAutocorrelation("Лягушка", 1), DELTA);
    }

    @Test
    void returns_0_if_no_characters_match_with_given_shift() {
        assertEquals(0., subject.computeAutocorrelation("Каравай", 1), DELTA);
    }

    @Test
    void counts_coefficient_correctly() {
        String input = "iloveinfobezlookatmeiamcool";
        assertEquals(1. / 7, subject.computeAutocorrelation(input, 6), DELTA);
    }

    @Test
    void returns_1_if_all_characters_are_equal() {
        assertEquals(1., subject.computeAutocorrelation("tttttttttt", 3), DELTA);
    }
}
