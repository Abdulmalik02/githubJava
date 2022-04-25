package space.banka.ifmo.infosec.vigenere.util;

import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FrequencyDistributionCSVLoader {

    public static FrequencyDistribution<Integer> loadFromResource(String resourceName) {
        Map<Integer, Double> frequencies = new HashMap<>();
        try (InputStream inputStream = FrequencyDistributionCSVLoader.class.getResourceAsStream(resourceName)) {
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8)) {
                while (scanner.hasNextLine()) {
                    String row = scanner.nextLine();
                    String[] cells = row.split(",");
                    int character = cells[0].codePointAt(0);
                    double frequency = Double.parseDouble(cells[1]);
                    frequencies.put(character, frequency);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load frequency distribution from CSV resource", e);
        }
        return new FrequencyDistribution<>(frequencies);
    }
}
