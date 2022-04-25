package space.banka.ifmo.infosec.vigenere.core.usecases;

import org.junit.jupiter.api.Test;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabets;
import space.banka.ifmo.infosec.vigenere.core.usecases.encrypt.Encryptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncryptorTest {

    private final Alphabet alphabet = Alphabets.lowerCaseLatin();
    private final Encryptor subject = new Encryptor();

    @Test
    void givenEmptyString_whenEncrypt_thenEmptyOutput() {
        String ciphertext = subject.encrypt("", "", alphabet);
        assertTrue(ciphertext.isEmpty());
    }

    @Test
    void givenPlaintextAndKeyOfEqualSizes_whenEncrypt_thenOk() {
        String ciphertext = subject.encrypt("zero", "echo", alphabet);
        assertEquals("dgyc", ciphertext);
    }

    @Test
    void givenKeyLongerThanPlaintext_whenEncrypt_thenOutputHasSizeOfPlaintext() {
        String plaintext = "hello";
        String key = "refrigerator";
        String ciphertext = subject.encrypt(plaintext, key, alphabet);

        assertEquals("yiqcw", ciphertext);
    }

    @Test
    void givenKeyShorterThanPlaintext_whenEncrypt_thenOutputHasSizeOfPlaintext() {
        String plaintext = "distinguishing";
        String key = "tiniest";
        String ciphertext = subject.encrypt(plaintext, key, alphabet);

        assertEquals("wqfbmfznqfpmfz", ciphertext);
    }
}
