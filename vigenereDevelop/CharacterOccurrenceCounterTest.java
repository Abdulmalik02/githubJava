package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class CharacterOccurrenceCounterTest {

    CharacterOccurrenceCounter subject = new CharacterOccurrenceCounter();

    @Test
    void countOccurrences() {
        String input = "abcbcc";
        CharacterOccurrenceStatistic statistic = subject.countOccurrences(input);
        assertSame(input, statistic.getOriginal());

        assertEquals(1, statistic.getOccurrencesOf('a'));
        assertEquals(2, statistic.getOccurrencesOf('b'));
        assertEquals(3, statistic.getOccurrencesOf('c'));
        assertEquals(0, statistic.getOccurrencesOf('#'));

        assertEquals(0.1667, statistic.getFrequencyOf('a'), 1e-4);
        assertEquals(0.3333, statistic.getFrequencyOf('b'), 1e-4);
        assertEquals(0.5000, statistic.getFrequencyOf('c'), 1e-4);
        assertEquals(0.0000, statistic.getFrequencyOf('#'), 1e-4);

        assertEquals(input.chars().count(), statistic.getTotalOccurrences());
    }
}
