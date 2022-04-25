package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.frequency_analysis;

import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.ColumnSlice;
import space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.caesar.KerckhoffsCaesarCracker;
import space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.keylen.autocorrelation.AutocorrelationKeyLengthCracker;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FrequencyAnalysisVigenereCipherCracker {

    private AutocorrelationKeyLengthCracker keyLengthCracker = new AutocorrelationKeyLengthCracker();
    private KerckhoffsCaesarCracker caesarCipherCracker = new KerckhoffsCaesarCracker();

    public CharSequence crackKey(CharSequence ciphertext,
                                 Alphabet alphabet,
                                 FrequencyDistribution<Integer> languageCharacterFrequencies) {

        int keyLength = keyLengthCracker.crackKeyLength(ciphertext);
        return IntStream.range(0, keyLength)
                .mapToObj(keyCharIndex -> {
                    CharSequence ciphertextColumn = new ColumnSlice(ciphertext, keyLength, keyCharIndex);
                    int caesarShift = caesarCipherCracker.crackRightShift(ciphertextColumn, alphabet, languageCharacterFrequencies);
                    int keyCharacter = alphabet.charAt(caesarShift);
                    return Character.toString(keyCharacter);
                })
                .collect(Collectors.joining());
    }
}
