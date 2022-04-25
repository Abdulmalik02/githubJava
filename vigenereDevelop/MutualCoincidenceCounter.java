package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.coincidence;

import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceCounter;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceStatistic;

public class MutualCoincidenceCounter {

    private CharacterOccurrenceCounter counter = new CharacterOccurrenceCounter();

    /**
     * Calculates the index of mutual coincidence for the two specified strings.
     *
     * <p>The <em>index of mutual coincidence</em>, or IMC for short,
     * is a probability of drawing two matching letters by randomly
     * selecting one character from each of the two given texts.
     *
     * @param strA the first string for which the IMC is computed.
     * @param strB the second string for which the IMC is computed.
     * @return the IMC for the two given strings.
     */
    public double computeIndexOfMutualCoincidence(CharSequence strA, CharSequence strB) {
        CharacterOccurrenceStatistic statisticB = counter.countOccurrences(strB);
        return strA.chars()
                       .map(character -> statisticB.getOccurrencesOf(character))
                       .sum() * 1.0 / (strA.length() * strB.length());
    }
}
