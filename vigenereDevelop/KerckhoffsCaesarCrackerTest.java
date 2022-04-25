package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.caesar;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;
import space.banka.ifmo.infosec.vigenere.util.FrequencyDistributionCSVLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KerckhoffsCaesarCrackerTest {

    @Test
    void computes_most_probable_shift_for_caesar_cipher() throws IOException {

        String ciphertext = IOUtils.resourceToString("/ciphertexts/caesar/lorem-ipsum/cipher.txt", StandardCharsets.UTF_8);
        Alphabet russianAlphabet = Alphabet.withChars("АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ");
        FrequencyDistribution<Integer> letterFrequenciesInRussian =
                FrequencyDistributionCSVLoader.loadFromResource("/frequencies/Russian.uppercase-only.csv");

        char actualKeyCharacter = 'П';
        int actualShift = russianAlphabet.indexOf(actualKeyCharacter);

        int crackedShift = new KerckhoffsCaesarCracker().crackRightShift(ciphertext, russianAlphabet, letterFrequenciesInRussian);
        assertEquals(actualShift, crackedShift);
    }
}
