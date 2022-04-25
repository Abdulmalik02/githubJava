package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.caesar;

import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceCounter;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceStatistic;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.pearson.PearsonChiSquaredTester;

import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KerckhoffsCaesarCracker {

    private final CharacterOccurrenceCounter occurrenceCounter = new CharacterOccurrenceCounter();
    private final PearsonChiSquaredTester pearsonTester = new PearsonChiSquaredTester();

    public int crackRightShift(CharSequence ciphertext, Alphabet alphabet, FrequencyDistribution<Integer> languageFrequencies) {
        CharacterOccurrenceStatistic ciphertextStatistic = occurrenceCounter.countOccurrences(ciphertext);
        Map<Integer, Double> shiftStatistics = IntStream.range(0, alphabet.length())
                .mapToObj(shift -> {
                    FrequencyDistribution<Integer> shiftedDistribution = shiftDistribution(alphabet, shift, languageFrequencies);
                    double chiSquared = pearsonTester.computeChiSquaredStatistic(ciphertextStatistic, shiftedDistribution);
                    System.out.println(MessageFormat.format("Probing shift value of {0} (key character ‘{1}’): chi-squared = {2}",
                            shift, Character.toString(alphabet.charAt(shift)), chiSquared));
                    return Map.entry(shift, chiSquared);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        Map.Entry<Integer, Double> mostProbableShiftAndChiSquared = shiftStatistics.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow();

        int selectedShift = mostProbableShiftAndChiSquared.getKey();

        int selectedKeyCharacter = alphabet.charAt(selectedShift);
        Double selectedChiSquared = mostProbableShiftAndChiSquared.getValue();
        System.out.println(MessageFormat.format("Selecting key character ‘{0}’ (shift = {1}) with chi-squared = {2}",
                Character.toString(selectedKeyCharacter), selectedShift, selectedChiSquared));

        return selectedShift;
    }

    FrequencyDistribution<Integer> shiftDistribution(Alphabet alphabet, int shift, FrequencyDistribution<Integer> frequencyDistribution) {
        String originalCharacters = alphabet.characters();
        Alphabet shiftedAlphabet = Alphabet.withChars(
                originalCharacters.substring(shift, originalCharacters.length()) +
                originalCharacters.subSequence(0, shift)
        );
        return new FrequencyDistribution<>(
                IntStream.range(0, alphabet.length())
                        .mapToObj(characterCode -> {
                            int character = alphabet.charAt(characterCode);
                            int shiftedCharacter = shiftedAlphabet.charAt(characterCode);
                            return new AbstractMap.SimpleEntry<>(shiftedCharacter, frequencyDistribution.getFrequencyOf(character));
                        })
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
}
