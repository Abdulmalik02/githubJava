package space.banka.ifmo.infosec.vigenere.entrypoints.commands;

import org.apache.commons.io.IOUtils;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabet;
import space.banka.ifmo.infosec.vigenere.core.entities.Alphabets;
import space.banka.ifmo.infosec.vigenere.core.usecases.decrypt.Decryptor;
import space.banka.ifmo.infosec.vigenere.entrypoints.Command;

import java.nio.charset.StandardCharsets;

import static space.banka.ifmo.infosec.vigenere.configuration.Main.ENV_VAR_NAME_FOR_KEY;
import static space.banka.ifmo.infosec.vigenere.configuration.Main.EXIT_CODE_ERROR;
import static space.banka.ifmo.infosec.vigenere.configuration.Main.EXIT_CODE_OK;

public class DecryptCommand implements Command {

    private final Decryptor decryptor = new Decryptor();

    @Override
    public int run(String[] args) throws Exception {
        String keyphrase = System.getenv(ENV_VAR_NAME_FOR_KEY);
        if (keyphrase == null) {
            System.err.println("Please specify encryption key as environment variable " + ENV_VAR_NAME_FOR_KEY);
            return EXIT_CODE_ERROR;
        }

        Alphabet alphabet = Alphabets.latin();

        System.err.println("Reading ciphertext from stdin.");
        String ciphertext = IOUtils.toString(System.in, StandardCharsets.UTF_8);

        System.err.println("Decrypting...");
        String plaintext = decryptor.decrypt(ciphertext, keyphrase, alphabet);

        System.err.println("Decryption complete.");
        System.out.print(plaintext);
        return EXIT_CODE_OK;
    }
}
