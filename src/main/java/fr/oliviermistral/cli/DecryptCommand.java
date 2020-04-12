package fr.oliviermistral.cli;

import fr.oliviermistral.business.CipherDirection;
import picocli.CommandLine.Command;
import picocli.CommandLine.Mixin;

@Command(name = "decrypt",
         aliases = "dc",
         description = "Decrypt <sequence> with Vigenere cipher",
         synopsisHeading = "%nUsage: ",
         descriptionHeading = "Description: ",
         parameterListHeading = "Parameters:%n",
         optionListHeading = "Options:%n",
         footerHeading = "Example:%nvigenere decrypt --key=MyPrivateKey \"Icatwhe ms dlc Hgvvvzrx gstfqp !\"")
public class DecryptCommand implements Runnable {

    @Mixin
    private ReusableOptions reusableOptions;

    @Override
    public void run() {
        reusableOptions.cipherAction(CipherDirection.DECRYPT);
    }

}
