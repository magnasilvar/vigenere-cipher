package fr.oliviermistral.cli;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import fr.oliviermistral.business.CipherDirection;

public class VigenereCommandTest {

    @TempDir
    public File tempDir;

    @Test
    public void testVigenereCommandWithoutArgs() throws IOException {
        try (final PrintStream originalErr = System.err) {
            final File output = new File(tempDir, "testVigenereWithoutArgs.txt");
            output.createNewFile();
            try (final PrintStream newErr = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setErr(newErr);
                VigenereCommand.main(new String[0]);
                Assertions.assertEquals("Missing required command", fileReader.nextLine());
                Assertions.assertTrue(fileReader.nextLine().isEmpty());
                Assertions.assertEquals("Usage: vigenere [-hV] [COMMAND]", fileReader.nextLine());
                Assertions.assertEquals("Description: Vigenere cipher CLI", fileReader.nextLine());
                Assertions.assertEquals("Options:", fileReader.nextLine());
                Assertions.assertEquals("  -h, --help      Show this help message and exit.", fileReader.nextLine());
                Assertions.assertEquals("  -V, --version   Print version information and exit.", fileReader.nextLine());
                Assertions.assertEquals("Commands:", fileReader.nextLine());
                Assertions.assertEquals("  encrypt, ec  Encrypt <sequence> with Vigenere cipher",
                        fileReader.nextLine());
                Assertions.assertEquals("  decrypt, dc  Decrypt <sequence> with Vigenere cipher",
                        fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setErr(originalErr);
            }
        }
    }

    @Test
    public void testVigenereCommandWithSubcommandError() throws IOException {
        try (final PrintStream originalErr = System.out) {
            final File output = new File(tempDir, "testVigenereWithDefaultAlphabet.txt");
            output.createNewFile();
            try (final PrintStream newErr = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setErr(newErr);
                VigenereCommand.main(new String[] { CipherDirection.DECRYPT.getDirection() });
                Assertions.assertEquals("Missing required options [--key=<key>, params[0..*]=<sequence>]",
                        fileReader.nextLine());
                Assertions.assertTrue(fileReader.nextLine().isEmpty());
                Assertions.assertEquals("Usage: vigenere decrypt [-a=<alphabet>] -k=<key> <sequence>",
                        fileReader.nextLine());
                Assertions.assertEquals("Description: Decrypt <sequence> with Vigenere cipher", fileReader.nextLine());
                Assertions.assertEquals("Parameters:", fileReader.nextLine());
                Assertions.assertEquals("      <sequence>    Sequence to process", fileReader.nextLine());
                Assertions.assertEquals("Options:", fileReader.nextLine());
                Assertions.assertEquals("  -a, --alphabet=<alphabet>", fileReader.nextLine());
                Assertions.assertEquals("                    Alphabet to use (optional / case insensitive) / default",
                        fileReader.nextLine());
                Assertions.assertEquals("                      alphabet: 'ABCDEFGHIJKLMNOPQRSTUVWXYZ')",
                        fileReader.nextLine());
                Assertions.assertEquals("  -k, --key=<key>   Secret key (case insensitive)", fileReader.nextLine());
                Assertions.assertEquals("Example:", fileReader.nextLine());
                Assertions.assertEquals("vigenere decrypt --key=MyPrivateKey \"Icatwhe ms dlc Hgvvvzrx gstfqp !\"",
                        fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setErr(originalErr);
            }
        }
    }

    @Test
    public void testVigenereDecrypt() throws IOException {
        try (final PrintStream originalOut = System.out) {
            final File output = new File(tempDir, "testVigenereDecrypt.txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                VigenereCommand.main(new String[] { CipherDirection.DECRYPT.getDirection(), "-k=MyPrivateKey",
                        "Icatwhe ms dlc Hgvvvème vmzlcd !" });
                Assertions.assertEquals("Welcome to the Vigenère cipher !", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

    @Test
    public void testVigenereEncrypt() throws IOException {
        try (final PrintStream originalOut = System.err) {
            final File output = new File(tempDir, "testVigenereEncrypt.txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                VigenereCommand.main(new String[] { CipherDirection.ENCRYPT.getDirection(), "-k=MyPrivateKey",
                        "Welcome to the Vigenère cipher !" });
                Assertions.assertEquals("Icatwhe ms dlc Hgvvvème vmzlcd !", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

}
