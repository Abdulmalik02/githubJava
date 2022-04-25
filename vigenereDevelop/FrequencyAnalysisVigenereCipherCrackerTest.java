package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.frequency_analysis;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;
import space.banka.ifmo.infosec.vigenere.util.FrequencyDistributionCSVLoader;
import space.banka.ifmo.infosec.vigenere.util.PropertiesLoader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FrequencyAnalysisVigenereCipherCrackerTest {

    private FrequencyAnalysisVigenereCipherCracker subject = new FrequencyAnalysisVigenereCipherCracker();

    @Test
    @Disabled
    void test_strugatsky() throws IOException {
        String ciphertext = IOUtils.resourceToString("/ciphertexts/vigenere/strugatsky/cipher.txt", StandardCharsets.UTF_8).strip();
        Properties keyProperties = PropertiesLoader.loadFromResource("/ciphertexts/vigenere/strugatsky/key.properties");
        Alphabet russianAlphabet = Alphabet.withChars(keyProperties.getProperty("alphabet"));
        String expectedKeyphrase = keyProperties.getProperty("key.phrase");

        FrequencyDistribution<Integer> letterFrequenciesInRussian =
                FrequencyDistributionCSVLoader.loadFromResource("/frequencies/Russian.uppercase-only.csv");

        CharSequence actualCrackedKeyphrase = subject.crackKey(ciphertext, russianAlphabet, letterFrequenciesInRussian);
        assertEquals(expectedKeyphrase, actualCrackedKeyphrase.toString());
    }
}
