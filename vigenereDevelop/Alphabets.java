package space.banka.ifmo.infosec.vigenere.core.entities;

public class Alphabets {

    private static final String LATIN_LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String LATIN_UPPERCASE = LATIN_LOWERCASE.toUpperCase();

    public static Alphabet lowerCaseLatin() {
        return Alphabet.withChars(LATIN_LOWERCASE);
    }

    public static Alphabet upperCaseLatin() {
        return Alphabet.withChars(LATIN_UPPERCASE);
    }

    public static Alphabet latin() {
        return Alphabet.withChars(LATIN_LOWERCASE + LATIN_UPPERCASE);
    }

}
