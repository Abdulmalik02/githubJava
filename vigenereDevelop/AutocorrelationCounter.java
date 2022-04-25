package space.banka.ifmo.infosec.vigenere.core.usecases.statistics.autocorrelation;

import java.util.stream.IntStream;

public class AutocorrelationCounter {

    /**
     * Computes the autocorrelation coefficient for the specified string
     * with the specified period.
     *
     * <p>The <em>autocorrelation coefficient</em> is a probability that
     * the character <code>string[i]</code> at a random index <code>i</code>
     * (where <code>i < string.length() - period</code>) is the same
     * as the character <code>string[i + period]</code>.
     *
     * @param string the {@link CharSequence} for which an autocorrelation should be calculated.
     * @param period the distance at which matching characters count as correlating.
     * @return the autocorrelation coefficient for the specified string and period.
     */
    public double computeAutocorrelation(CharSequence string, int period) {

        int truncatedLength = string.length() - period;

        long numCorrelations = IntStream
                .range(0, truncatedLength)
                .filter(index -> {
                    int character = string.charAt(index);
                    int shiftedCharacter = string.charAt(index + period);
                    return character == shiftedCharacter;
                })
                .count();

        return (double) numCorrelations / truncatedLength;
    }

    public CharSequence stringShift(CharSequence string, int period) {
        return string.subSequence(period, string.length());
    }

    public CharSequence removeAllNonLetters(CharSequence string) {
        String[] stringArray = string.toString().split("[^a-z]+");
        StringBuilder result = new StringBuilder();
        for (String s : stringArray) {
            result.append(s);
        }
        return result.toString();
    }
}
