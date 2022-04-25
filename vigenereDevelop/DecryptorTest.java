package space.banka.ifmo.infosec.vigenere.core.usecases;

import org.junit.jupiter.api.Test;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabets;
import space.banka.ifmo.infosec.vigenere.core.usecases.decrypt.Decryptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DecryptorTest {

    private final Alphabet alphabet = Alphabets.lowerCaseLatin();
    private Decryptor subject = new Decryptor();

    @Test
    void givenEmptyCiphertext_whenDecrypt_thenEmptyOutput() {
        String plaintext = subject.decrypt("", "", alphabet);
        assertTrue(plaintext.isEmpty());
    }

    @Test
    void givenCiphertextAndKeyOfEqualSizes_whenEncrypt_thenOk() {
        String plaintext = subject.decrypt("dgyc", "echo", alphabet);
        assertEquals("zero", plaintext);
    }

    @Test
    void givenKeyLongerThanPlaintext_whenEncrypt_thenOutputHasSizeOfPlaintext() {
        String ciphertext = "yiqcw";
        String key = "refrigerator";
        String plaintext = subject.decrypt(ciphertext, key, alphabet);

        assertEquals("hello", plaintext);
    }

    @Test
    void givenKeyShorterThanPlaintext_whenEncrypt_thenOutputHasSizeOfPlaintext() {
        String ciphertext = "wqfbmfznqfpmfz";
        String key = "tiniest";
        String plaintext = subject.decrypt(ciphertext, key, alphabet);

        assertEquals("distinguishing", plaintext);
    }
}
