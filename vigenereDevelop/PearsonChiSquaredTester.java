package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.pearson;

import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.CharacterOccurrenceStatistic;
import space.banka.ifmo.infosec.vigenere.core.usecases.statistics.occurrences.FrequencyDistribution;

public class PearsonChiSquaredTester {

    /**
     * Calculates the value of the Pearson's chi-squared statistic
     * for the two specified distributions.
     *
     * @param actualDistribution      the actual observed distribution of elements' frequencies.
     * @param theoreticalDistribution the expected theoretical distribution of elements' frequencies.
     * @return the value of the chi-squared test statistic for the given distributions.
     */
    public double computeChiSquaredStatistic(CharacterOccurrenceStatistic actualDistribution,
                                             FrequencyDistribution<Integer> theoreticalDistribution) {
        return actualDistribution.getTotalOccurrences() *
               theoreticalDistribution.keySet()
                       .stream()
                       .mapToDouble(it -> {
                           double theoreticalFrequency = theoreticalDistribution.getFrequencyOf(it);
                           double actualFrequency = actualDistribution.getFrequencyOf(it);
                           return Math.pow(actualFrequency - theoreticalFrequency, 2.) / theoreticalFrequency;
                       })
                       .sum();
    }
}
