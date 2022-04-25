package space.banka.ifmo.infosec.vigenere.core.usecases.encrypt;

import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.VigenereTable;

import java.util.stream.IntStream;

public class Encryptor {

    public String encrypt(String plaintext, String key, Alphabet alphabet) {
        VigenereTable table = new VigenereTable(alphabet);
        StringBuilder cipherText = new StringBuilder(plaintext.length());

        IntStream.range(0, plaintext.length()).forEach(indexInPlaintext -> {
            int plaintextCharacter = plaintext.charAt(indexInPlaintext);
            int keyCharacter = key.charAt(indexInPlaintext % key.length());
            int encryptedCharacter = table.encode(plaintextCharacter, keyCharacter);
            cipherText.append((char) encryptedCharacter);
        });

        return cipherText.toString();
    }
}
