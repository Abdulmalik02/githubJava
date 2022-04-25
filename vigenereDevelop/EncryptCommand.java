package space.banka.ifmo.infosec.vigenere.entrypoints.commands;

import org.apache.commons.io.IOUtils;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabets;
import space.banka.ifmo.infosec.vigenere.core.usecases.encrypt.Encryptor;
import space.banka.ifmo.infosec.vigenere.entrypoints.Command;

import java.nio.charset.StandardCharsets;

import static space.banka.ifmo.infosec.vigenere.configuration.Main.ENV_VAR_NAME_FOR_KEY;
import static space.banka.ifmo.infosec.vigenere.configuration.Main.EXIT_CODE_ERROR;
import static space.banka.ifmo.infosec.vigenere.configuration.Main.EXIT_CODE_OK;

public class EncryptCommand implements Command {

    private final Encryptor encryptor = new Encryptor();

    @Override
    public int run(String[] args) throws Exception {
        String keyphrase = System.getenv(ENV_VAR_NAME_FOR_KEY);
        if (keyphrase == null) {
            System.err.println("Please specify encryption key as environment variable " + ENV_VAR_NAME_FOR_KEY);
            return EXIT_CODE_ERROR;
        }

        Alphabet alphabet = Alphabets.latin();

        System.err.println("Reading plaintext from stdin.");
        String plaintext = IOUtils.toString(System.in, StandardCharsets.UTF_8);

        System.err.println("Encrypting...");
        String ciphertext = encryptor.encrypt(plaintext, keyphrase, alphabet);

        System.err.println("Encryption complete.");
        System.out.print(ciphertext);
        return EXIT_CODE_OK;
    }
}
