package space.banka.ifmo.infosec.vigenere.core.entities;

import space.banka.ifmo.infosec.vigenere.core.exceptions.IllegalKeyCharacterException;

import static space.banka.ifmo.infosec.vigenere.core.entities.Alphabet.NOT_FOUND;

public class VigenereTable {

    private final Alphabet alphabet;

    public VigenereTable(Alphabet alphabet) {
        this.alphabet = alphabet;
    }

    public int encode(int character, int keyCharacter) {
        int keyAlphabetIndex = alphabet.indexOf(keyCharacter);
        if (keyAlphabetIndex == NOT_FOUND) {
            throw new IllegalKeyCharacterException(keyCharacter, alphabet);
        }

        return shiftCharacterByOffset(character, +keyAlphabetIndex);
    }

    public int decode(int character, int keyCharacter) {
        int keyAlphabetIndex = alphabet.indexOf(keyCharacter);
        if (keyAlphabetIndex == NOT_FOUND) {
            throw new IllegalKeyCharacterException(keyCharacter, alphabet);
        }

        return shiftCharacterByOffset(character, -keyAlphabetIndex);
    }

    private int shiftCharacterByOffset(int character, int offset) {
        int indexOfCharInAlphabet = alphabet.indexOf(character);
        if (indexOfCharInAlphabet == NOT_FOUND) {
            return character;
        }

        int decodedAlphabetIndex = Math.floorMod(indexOfCharInAlphabet + offset, alphabet.length());
        return alphabet.charAt(decodedAlphabetIndex);
    }
}
