package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences;

import java.util.HashMap;
import java.util.Map;

public class CharacterOccurrenceCounter {

    public CharacterOccurrenceStatistic countOccurrences(CharSequence string) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        string.chars().forEach(character -> occurrences.merge(character, 1, Integer::sum));
        return new CharacterOccurrenceStatistic(string, occurrences);
    }
}
