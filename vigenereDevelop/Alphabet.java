package space.banka.ifmo.infosec.vigenere.core.entities;

import java.util.HashMap;

public class Alphabet {

    public static final int NOT_FOUND = -1;

    protected final String chars;
    protected final HashMap<Integer, Integer> charToIndex;

    public Alphabet(String chars, HashMap<Integer, Integer> charToIndex) {
        this.chars = chars;
        this.charToIndex = charToIndex;
    }

    public static Alphabet withChars(String alphabetChars) {
        return new Alphabet(alphabetChars, Alphabet.buildCharToAlphabetIndexMap(alphabetChars));
    }

    private static HashMap<Integer, Integer> buildCharToAlphabetIndexMap(CharSequence alphabet) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int alphabetIndex = 0; alphabetIndex < alphabet.length(); alphabetIndex++) {
            int alphabetChar = alphabet.charAt(alphabetIndex);
            map.put(alphabetChar, alphabetIndex);
        }
        return map;
    }

    /**
     * Finds the index within the alphabet of the specified character.
     *
     * @param character character to find in the alphabet.
     * @return zero-based index of the character in the alphabet,
     * or {@link Alphabet#NOT_FOUND} if the alphabet does not contain
     * such a character.
     */
    public int indexOf(int character) {
        return charToIndex.getOrDefault(character, Alphabet.NOT_FOUND);
    }

    /**
     * Returns the character located in the alphabet at the specified index.
     *
     * @param index zero-based index of a character in the alphabet.
     * @return the character at the given index.
     */
    public int charAt(int index) {
        return chars.charAt(index);
    }

    /**
     * Returns the number of characters in the alphabet.
     *
     * @return the alphabet's length.
     */
    public int length() {
        return chars.length();
    }

    /**
     * Returns all characters of the alphabet in order of their occurrence in the alphabet.
     *
     * @return the alphabet's characters.
     */
    public String characters() {
        return chars;
    }

    @Override
    public String toString() {
        return "Alphabet{" +
               "chars='" + chars + '\'' +
               '}';
    }
}
