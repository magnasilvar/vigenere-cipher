package fr.oliviermistral;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereUtilsTest {

    @Test
    public void testEncryptWithDefaultAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT.getDirection(), null, "MyPrivateKey",
                "Welcome to the Vigenère cipher !".toCharArray());
        Assertions.assertEquals("Cipher text: Icatwhe ms dlc Hgvvvème vmzlcd !", VigenereUtils.cipher(data));
    }

    @Test
    public void testEncryptWithCustomAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.ENCRYPT.getDirection(), "AbCdEfGhIjKlprtvym",
                "MyPrivateKey", "Welcome to the Vigenère cipher !".toCharArray());
        Assertions.assertEquals("Cipher text: Wdjtopp lo tdi Hpednèly vyjham !", VigenereUtils.cipher(data));
    }

    @Test
    public void testDecryptWithDefaultAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.DECRYPT.getDirection(), null, "MyPrivateKey",
                "Icatwhe ms dlc Hgvvvème vmzlcd !".toCharArray());
        Assertions.assertEquals("Plain text: Welcome to the Vigenère cipher !", VigenereUtils.cipher(data));
    }

    @Test
    public void testDecryptWithCustomAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(CipherDirection.DECRYPT.getDirection(), "AbCdEfGhIjKlprtvym",
                "MyPrivateKey", "Wdjtopp lo tdi Hpednèly vyjham !".toCharArray());
        Assertions.assertEquals("Plain text: Welcome to the Vigenère cipher !", VigenereUtils.cipher(data));
    }

}
