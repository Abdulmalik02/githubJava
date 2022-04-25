package space.banka.ifmo.infosec.vigenere.core.usecases.cryptanalysis.keylen.friedman;

import space.banka.ifmo.infosec.vigenere.core.entities.ColumnSlice;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.coincidence.CoincidenceCounter;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FriedmanTester {

    private CoincidenceCounter coincidenceCounter = new CoincidenceCounter();

    public double estimateKeySize(CharSequence ciphertext,
                                  double languageIndexOfCoincidence) {

        Map<Integer, Double> coincidenceByKeyLength = IntStream.rangeClosed(3, 25)
                .parallel()
                .mapToObj(numColumns -> {
                    double totalIndexOfCoincidence = IntStream.range(0, numColumns)
                            .mapToObj(columnIndex -> new ColumnSlice(ciphertext, numColumns, columnIndex))
                            .mapToDouble(column -> coincidenceCounter.countIndexOfCoincidence(column))
                            .sum();
                    double averageIndexOfCoincidence = totalIndexOfCoincidence / numColumns;
                    return new AbstractMap.SimpleEntry<>(numColumns, averageIndexOfCoincidence);
                })
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        return coincidenceByKeyLength.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .peek(indexByLength -> {
                    // TODO: Do not output, better return in a report to allow automated analysis
                    System.out.println(
                            "For key length " + indexByLength.getKey() + ", " +
                            "mean index of coincidence is " + indexByLength.getValue());
                })
                .min(Map.Entry.comparingByValue(
                        Comparator.comparing((Double index) -> {
                            return Math.abs(languageIndexOfCoincidence - index);
                        })))
                .orElseThrow(() -> new IllegalStateException("unreachable"))
                .getKey();
    }
}
