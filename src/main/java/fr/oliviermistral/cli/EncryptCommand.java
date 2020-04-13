package fr.oliviermistral.cli;

import fr.oliviermistral.business.CipherDirection;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "encrypt",
        aliases = "ec",
        description = "Encrypt <sequence> with Vigenere cipher",
        synopsisHeading = "%nUsage: ",
        descriptionHeading = "Description: ",
        parameterListHeading = "Parameters:%n",
        optionListHeading = "Options:%n",
        footerHeading = "Example:%nvigenere encrypt --key=MyPrivateKey \"Welcome to the Vigenere cipher !\"")
public class EncryptCommand implements Runnable {

    @Mixin
    private ReusableOptions reusableOptions;

    @Override
    public void run() {
        reusableOptions.cipherAction(CipherDirection.ENCRYPT);
    }

}
