package fr.oliviermistral;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class VigenereTest {

    @TempDir
    public File tempDir;

    @Test
    public void testVigenereWithoutArgs() throws IOException {
        try (final PrintStream originalOut = System.out) {
            final File output = new File(tempDir, "testVigenereWithoutArgs.txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                Vigenere.main(new String[0]);
                Assertions.assertEquals("Bad use ! Syntax : \"encrypted sequence\" \"key\" \"alphabet (optional)\"", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

    @Test
    public void testVigenereWithDefaultAlphabet() throws IOException {
        try (final PrintStream originalOut = System.out) {
            final File output = new File(tempDir, "testVigenereWithDefaultAlphabet.txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                Vigenere.main(new String[] { "Icatwhe ms dlc Hgvvvème vmzlcd !", "MyPrivateKey" });
                Assertions.assertEquals("key=MYPRIVATEKEY", fileReader.nextLine());
                Assertions.assertEquals("alphabet=ABCDEFGHIJKLMNOPQRSTUVWXYZ", fileReader.nextLine());
                Assertions.assertEquals("Decrypted sequence: Welcome to the Vigenère cipher !", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

    @Test
    public void testVigenereWithCustomAlphabet() throws IOException {
        try (final PrintStream originalOut = System.out) {
            final File output = new File(tempDir, "testVigenereWithCustomAlphabet.txt");
            output.createNewFile();
            try (final PrintStream newOut = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setOut(newOut);
                Vigenere.main(new String[] { "Wdjtopp lo tdi Hpednèly vyjham !", "MyPrivateKey", "AbCdEfGhIjKlprtvym" });
                Assertions.assertEquals("key=MYPRIVATEKEY", fileReader.nextLine());
                Assertions.assertEquals("alphabet=ABCDEFGHIJKLPRTVYM", fileReader.nextLine());
                Assertions.assertEquals("Decrypted sequence: Welcome to the Vigenère cipher !", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalOut);
            }
        }
    }

    @Test
    public void testVigenereWithErrors() throws IOException {
        try (final PrintStream originalErr = System.err) {
            final File output = new File(tempDir, "testVigenereWithErrors.txt");
            output.createNewFile();
            try (final PrintStream newErr = new PrintStream(output); Scanner fileReader = new Scanner(output)) {
                System.setErr(newErr);
                Vigenere.main(new String[] { "", "MyPrivateKey", "Abcdac" });
                Assertions.assertEquals("Settings errors:", fileReader.nextLine());
                Assertions.assertEquals("- Alphabet contains non unique characters: [A, C]", fileReader.nextLine());
                Assertions.assertEquals("- Key contains forbidden characters (not included in the alphabet): [P, R, T, E, V, Y, I, K, M]", fileReader.nextLine());
                Assertions.assertFalse(fileReader.hasNext());
                System.setOut(originalErr);
            }
        }
    }

}
