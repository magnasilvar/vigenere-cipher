package fr.oliviermistral.business;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereDataTest {

    @Test
    public void testVigenereDataEncrypt() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, null, "KEY", null);
        Assertions.assertEquals(CipherDirection.ENCRYPT, data.getCipherDirection());
    }

    @Test
    public void testVigenereDataDecrypt() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.DECRYPT, null, "KEY", null);
        Assertions.assertEquals(CipherDirection.DECRYPT, data.getCipherDirection());
    }

    @Test
    public void testVigenereDataWithoutDirection() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData(null, null, "a", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Error:", errors.next());
        Assertions.assertEquals("- Cipher direction [encrypt|decrypt] is mandatory", errors.next());
    }

    @Test
    public void testVigenereDataWithDefaultAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, null, "KEY", null);
        Assertions.assertEquals(VigenereData.DEFAULT_ALPHABET, data.getAlphabet());
        Assertions.assertEquals(26, data.getAlphabetLength());
    }

    @Test
    public void testVigenereDataWithCustomAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, "ABCZ", "CZ", "ENCRYPTED".toCharArray());
        Assertions.assertEquals("ABCZ", data.getAlphabet());
        Assertions.assertEquals(4, data.getAlphabetLength());
    }

    @Test
    public void testVigenereDataKey() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, null, "KEY", null);
        Assertions.assertEquals("KEY", data.getKey());
        Assertions.assertEquals(3, data.getKeyLength());
    }

    @Test
    public void testVigenereDataText() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, null, "KEY", "ENCRYPTED".toCharArray());
        Assertions.assertArrayEquals("ENCRYPTED".toCharArray(), data.getChars());
    }

    @Test
    public void testVigenereDataWithLowercase() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT, "abcdeÉL", "clé", null);
        Assertions.assertEquals("ABCDEÉL", data.getAlphabet());
        Assertions.assertEquals("CLÉ", data.getKey());
    }

    @Test
    public void testVigenereDataThrowsNonUniqueAlphabetCharsException() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData(CipherDirection.ENCRYPT, "Abcdeac", "a", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Error:", errors.next());
        Assertions.assertEquals("- Alphabet contains non unique characters: [A, C]", errors.next());
    }

    @Test
    public void testVigenereDataThrowsKeyForbiddenCharsException() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData(CipherDirection.ENCRYPT, "Abcde", "MyPrivateKey", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Error:", errors.next());
        Assertions.assertEquals(
                "- Key contains forbidden characters (not included in the alphabet): [P, R, T, V, Y, I, K, M]",
                errors.next());
    }

    @Test
    public void testVigenereDataThrowsMultipleSettingsException() throws VigenereSettingsException {
        final VigenereSettingsException exception = Assertions.assertThrows(VigenereSettingsException.class,
                () -> new VigenereData(null, "Abcdeac", "MyPrivateKey", null));
        final Iterator<String> errors = exception.getMessage().lines().iterator();
        Assertions.assertEquals("Errors:", errors.next());
        Assertions.assertEquals("- Cipher direction [encrypt|decrypt] is mandatory", errors.next());
        Assertions.assertEquals("- Alphabet contains non unique characters: [A, C]", errors.next());
        Assertions.assertEquals(
                "- Key contains forbidden characters (not included in the alphabet): [P, R, T, V, Y, I, K, M]",
                errors.next());
    }

}
