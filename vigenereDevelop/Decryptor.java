package space.banka.ifmo.infosec.vigenere.core.usecases.decrypt;

import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.VigenereTable;

import java.util.stream.IntStream;

public class Decryptor {

    public String decrypt(String ciphertext, String key, Alphabet alphabet) {
        VigenereTable table = new VigenereTable(alphabet);
        StringBuilder plaintext = new StringBuilder(ciphertext.length());

        IntStream.range(0, ciphertext.length()).forEach(indexInCiphertext -> {
            int ciphertextCharacter = ciphertext.charAt(indexInCiphertext);
            int keyCharacter = key.charAt(indexInCiphertext % key.length());
            int decryptedCharacter = table.decode(ciphertextCharacter, keyCharacter);
            plaintext.append((char) decryptedCharacter);
        });

        return plaintext.toString();
    }
}
