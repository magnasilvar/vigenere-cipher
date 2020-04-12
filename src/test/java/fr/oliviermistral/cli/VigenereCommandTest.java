package fr.oliviermistral.cli;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import picocli.CommandLine;

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
                final VigenereCommand vigenere = new VigenereCommand();
                final CommandLine cmd = new CommandLine(vigenere);
                final int exitCode = cmd.execute();
                Assertions.assertEquals(2, exitCode);
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

}
