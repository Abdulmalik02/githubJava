package space.banka.ifmo.infosec.vigenere.core.exceptions;

import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;

import java.text.MessageFormat;

public class IllegalKeyCharacterException extends RuntimeException {

    public IllegalKeyCharacterException(int character, Alphabet alphabet) {
        super(MessageFormat.format(
                "Illegal key character {1} (byte {0}) is not allowed in alphabet {3}",
                character, Character.getName(character), alphabet));
    }
}
