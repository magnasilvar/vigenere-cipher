package fr.oliviermistral.cli;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Spec;

@Command(name = "vigenere",
         description = "Vigenere cipher CLI",
         subcommands = { EncryptCommand.class, DecryptCommand.class },
         mixinStandardHelpOptions = true,
         versionProvider = ManifestVersionProvider.class,
         synopsisHeading = "%nUsage: ",
         descriptionHeading = "Description: ",
         optionListHeading = "Options:%n")
public class VigenereCommand implements Runnable {

    @Spec
    private CommandSpec spec;

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Missing required command");
    }

    public static void main(final String[] args) {
        new CommandLine(new VigenereCommand()).execute(args);
    }

}
