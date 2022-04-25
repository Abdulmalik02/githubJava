package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences;

import java.util.Map;
import java.util.Set;

public class FrequencyDistribution<T> {

    private final Map<T, Double> frequencies;

    public FrequencyDistribution(Map<T, Double> frequencies) {
        this.frequencies = frequencies;
    }

    public Set<T> keySet() {
        return frequencies.keySet();
    }

    public double getFrequencyOf(T element) {
        return frequencies.getOrDefault(element, 0.);
    }
}
