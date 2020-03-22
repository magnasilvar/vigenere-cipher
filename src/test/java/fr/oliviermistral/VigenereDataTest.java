package fr.oliviermistral;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereDataTest {

    @Test
    public void testVigenereDataWithDefaultAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(null, "KEY", "ENCRYPTED".toCharArray());
        Assertions.assertEquals(VigenereData.DEFAULT_ALPHABET, data.getAlphabet());
        Assertions.assertEquals(26, data.getAlphabetLength());
        Assertions.assertEquals("KEY", data.getKey());
        Assertions.assertEquals(3, data.getKeyLength());
        Assertions.assertArrayEquals("ENCRYPTED".toCharArray(), data.getChars());
    }

    @Test
    public void testVigenereDataWithLowercase() throws VigenereSettingsException {
        final VigenereData data = new VigenereData("abcdeÉL", "clé", "ENCRYPTED".toCharArray());
        Assertions.assertEquals("ABCDEÉL", data.getAlphabet());
        Assertions.assertEquals(7, data.getAlphabetLength());
        Assertions.assertEquals("CLÉ", data.getKey());
        Assertions.assertEquals(3, data.getKeyLength());
        Assertions.assertArrayEquals("ENCRYPTED".toCharArray(), data.getChars());
    }

    @Test
    public void testVigenereDataThrowsNonUniqueAlphabetCharsException() throws VigenereSettingsException {
        final VigenereSettingsException exception =
            Assertions.assertThrows(VigenereSettingsException.class, () -> new VigenereData("Abcdeac", "a", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Settings errors:", errors.next());
        Assertions.assertEquals("- Alphabet contains non unique characters: [A, C]", errors.next());
    }

    @Test
    public void testVigenereDataThrowsKeyForbiddenCharsException() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData("Abcde", "MyPrivateKey", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Settings errors:", errors.next());
        Assertions.assertEquals(
                "- Key contains forbidden characters (not included in the alphabet): [P, R, T, V, Y, I, K, M]",
                errors.next());
    }

    @Test
    public void testVigenereDataThrowsSettingsException() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData("Abcdeac", "MyPrivateKey", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Settings errors:", errors.next());
        Assertions.assertEquals("- Alphabet contains non unique characters: [A, C]", errors.next());
        Assertions.assertEquals("- Key contains forbidden characters (not included in the alphabet): [P, R, T, V, Y, I, K, M]", errors.next());
    }

}
