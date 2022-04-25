package space.banka.ifmo.infosec.vigenere.configuration;

import space.banka.ifmo.infosec.vigenere.entrypoints.Command;
import space.banka.ifmo.infosec.vigenere.entrypoints.commands.CryptanalyzeCommand;
import space.banka.ifmo.infosec.vigenere.entrypoints.commands.DecryptCommand;
import space.banka.ifmo.infosec.vigenere.entrypoints.commands.EncryptCommand;

import java.util.Map;

public class Main {

    public static final String ENV_VAR_NAME_FOR_KEY = "VIGENERE_KEY";

    public static final int EXIT_CODE_OK = 0;
    public static final int EXIT_CODE_ERROR = 1;

    private static final Map<String, Command> COMMANDS = Map.of(
            "encrypt", new EncryptCommand(),
            "decrypt", new DecryptCommand(),
            "analyze", new CryptanalyzeCommand()
    );

    public static void main(String[] args) throws Exception {
        int exitCode = new Main().run(args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws Exception {
        if (args.length != 1 || !COMMANDS.containsKey(args[0])) {
            String availableCommands = String.join(", ", COMMANDS.keySet());
            System.err.println("Please specify one of commands: " + availableCommands);
            return EXIT_CODE_ERROR;
        }

        Command command = COMMANDS.get(args[0]);
        return command.run(args);
    }
}
