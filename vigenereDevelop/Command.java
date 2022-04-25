package space.banka.ifmo.infosec.vigenere.entrypoints;

public interface Command {

    /**
     * Run the command.
     *
     * @param args command-line arguments
     * @return a non-negative exit code: 0 on success, a positive integer on error
     * @throws Exception in case of any error
     */
    int run(String[] args) throws Exception;
}
