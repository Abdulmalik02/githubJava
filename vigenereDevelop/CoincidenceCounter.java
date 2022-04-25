package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.coincidence;

import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceCounter;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceStatistic;

public class CoincidenceCounter {

    private final CharacterOccurrenceCounter occurrenceCounter = new CharacterOccurrenceCounter();

    /**
     * Calculates the index of coincidence for the specified string.
     *
     * <p>The <em>index of coincidence</em>, or IC for short, is a probability
     * of drawing two matching letters by randomly selecting two characters
     * from a given text.
     *
     * @param string the string for which the IC is computed.
     * @return the IC for the given string.
     */
    public double countIndexOfCoincidence(CharSequence string) {
        CharacterOccurrenceStatistic statistic = occurrenceCounter.countOccurrences(string);
        int sumOfOccurrences = statistic.characterSet()
                .stream()
                .mapToInt(character -> {
                    int optionsToPickFromWholeString = statistic.getOccurrencesOf(character);
                    int optionsToPickFromRestOfString = optionsToPickFromWholeString - 1;
                    return optionsToPickFromWholeString * optionsToPickFromRestOfString;
                }).sum();

        int total = statistic.getTotalOccurrences();
        return (double) sumOfOccurrences / total / (total - 1);
    }
}
