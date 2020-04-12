package fr.oliviermistral.cli;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
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
            try (final PrintStream newErr = new PrintStream(output); final Scanner fileReader = new Scanner(output)) {
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
    public void testVigenereCommandDecryptError() throws IOException {
        try (final PrintStream originalErr = System.out) {
            final File output = new File(tempDir, "testVigenereCommandDecryptError.txt");
            output.createNewFile();
            try (final PrintStream newErr = new PrintStream(output); final Scanner fileReader = new Scanner(output)) {
                System.setErr(newErr);
                VigenereCommand
                        .main(new String[] { CipherDirection.DECRYPT.getDirection(), "-a=bbb", "-k=a", "sequence" });
                Assertions.assertEquals("Errors:", fileReader.nextLine());
                Assertions.assertEquals("- Alphabet contains non unique characters: [B]", fileReader.nextLine());
                Assertions.assertEquals("- Key contains forbidden characters (not included in the alphabet): [A]",
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
    public void testVigenereCommandDecryptWithKey() throws IOException {
        assertSuccess("DecryptWithKey", CipherDirection.DECRYPT, "--key=MyPrivateKey", null,
                "Icatwhe ms dlc Hgvvvème vmzlcd !", "Welcome to the Vigenère cipher !");
    }

    @Test
    public void testVigenereCommandDecryptWithKeyAlias() throws IOException {
        assertSuccess("DecryptWithKeyAlias", CipherDirection.DECRYPT, "-k=MyPrivateKey", null,
                "Icatwhe ms dlc Hgvvvème vmzlcd !", "Welcome to the Vigenère cipher !");
    }

    @Test
    public void testVigenereCommandDecryptWithAlphabet() throws IOException {
        assertSuccess("DecryptWithAlphabet", CipherDirection.DECRYPT, "-k=MyPrivateKey", "--alphabet=AbCdEfGhIjKlprtvym",
                "Wdjtopp lo tdi Hpednèly vyjham !", "Welcome to the Vigenère cipher !");
    }

    @Test
    public void testVigenereCommandDecryptWithAlphabetAlias() throws IOException {
        assertSuccess("DecryptWithAlphabetAlias", CipherDirection.DECRYPT, "-k=MyPrivateKey", "-a=AbCdEfGhIjKlprtvym",
                "Wdjtopp lo tdi Hpednèly vyjham !", "Welcome to the Vigenère cipher !");
    }

    @Test
    public void testVigenereCommandEncrypt() throws IOException {
        assertSuccess("Encrypt", CipherDirection.ENCRYPT, "-k=MyPrivateKey", null, "Welcome to the Vigenère cipher !",
                "Icatwhe ms dlc Hgvvvème vmzlcd !");
    }

    private void assertSuccess(final String fileName, final CipherDirection direction, final String key,
            final String alphabet, final String sequence, final String expectedResult) throws IOException {
        try (final PrintStream originalOut = System.err) {
            final File output = new File(tempDir, "testVigenereCommand" + fileName + ".txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); final Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                final List<String> args = new ArrayList<>();
                args.addAll(List.of(direction.getDirection(), key, sequence));
                if (alphabet != null) {
                    args.add(alphabet);
                }
                VigenereCommand.main(args.toArray(new String[args.size()]));
                Assertions.assertEquals(expectedResult, fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

}
