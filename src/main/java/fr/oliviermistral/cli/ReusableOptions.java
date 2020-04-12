package fr.oliviermistral.cli;

import fr.oliviermistral.business.CipherDirection;
import fr.oliviermistral.business.VigenereData;
import fr.oliviermistral.business.VigenereSettingsException;
import fr.oliviermistral.business.VigenereUtils;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;
import picocli.CommandLine.Model.CommandSpec;

public class ReusableOptions {

    @Spec
    private CommandSpec spec;

    @Option(names = { "-k", "--key" }, required = true, description = "Secret key (case insensitive)")
    private String key;

    @Option(names = { "-a",
            "--alphabet" }, description = "Alphabet to use (optional / case insensitive) / default alphabet: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')")
    private String alphabet;

    @Parameters(description = "Sequence to process")
    private String sequence;

    public void cipherAction(final CipherDirection cipherDirection) {
        try {
            final VigenereData data = new VigenereData(cipherDirection, alphabet, key, sequence.toCharArray());
            spec.commandLine().getOut().println(VigenereUtils.cipher(data));
        } catch (final VigenereSettingsException e) {
            throw new CommandLine.ParameterException(spec.commandLine(), e.getMessage(), e);
        }
    }

}
