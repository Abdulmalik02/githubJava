package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences;

import java.util.Map;
import java.util.Set;

public class CharacterOccurrenceStatistic {

    private final CharSequence original;
    private final Map<Integer, Integer> characterOccurences;

    public CharacterOccurrenceStatistic(CharSequence original, Map<Integer, Integer> characterOccurrences) {
        this.original = original;
        this.characterOccurences = characterOccurrences;
    }

    public Set<Integer> characterSet() {
        return characterOccurences.keySet();
    }

    public CharSequence getOriginal() {
        return original;
    }

    public int getOccurrencesOf(int character) {
        return characterOccurences.getOrDefault(character, 0);
    }

    public int getTotalOccurrences() {
        return characterOccurences.values()
                .stream()
                .mapToInt(i -> i)
                .sum();
    }

    public double getFrequencyOf(int character) {
        return (double) getOccurrencesOf(character) / getTotalOccurrences();
    }

    @Override
    public String toString() {
        return "CharacterOccurrenceStatistic{" +
               "original=" + original +
               ", characterOccurences=" + characterOccurences +
               '}';
    }
}
