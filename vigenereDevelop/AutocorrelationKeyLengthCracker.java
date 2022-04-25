package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.keylen.autocorrelation;

import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.autocorrelation.AutocorrelationCounter;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class AutocorrelationKeyLengthCracker {

    private static final int MIN_KEY_LENGTH = 3;
    private static final int MAX_KEY_LENGTH = 25;

    private final AutocorrelationCounter autocorrelationCounter = new AutocorrelationCounter();

    public int crackKeyLength(CharSequence ciphertext) {
        Optional<AbstractMap.SimpleEntry<Integer, Double>> maxAutocorrelation =
                IntStream.range(MIN_KEY_LENGTH, MAX_KEY_LENGTH)
                        .parallel()
                        .mapToObj(keyLength -> {
                            double autocorrelation = autocorrelationCounter.computeAutocorrelation(ciphertext, keyLength);
                            return new AbstractMap.SimpleEntry<>(keyLength, autocorrelation);
                        })
                        .peek(entry -> {
                            Integer keyLength = entry.getKey();
                            Double autocorrelation = entry.getValue();
                            System.out.println("For key length " + keyLength + ", " +
                                               "autocorrelation coefficient is " + autocorrelation);
                        })
                        .max(Map.Entry.comparingByValue());

        return maxAutocorrelation
                .orElseThrow(() -> new IllegalStateException("unreachable"))
                .getKey();
    }
}
