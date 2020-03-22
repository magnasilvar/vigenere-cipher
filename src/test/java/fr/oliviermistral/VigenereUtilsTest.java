package fr.oliviermistral;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class VigenereUtilsTest {

    @Test
    public void testDecryptWithDefaultAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData(null, "MyPrivateKey", "Icatwhe ms dlc Hgvvvème vmzlcd !".toCharArray());
        Assertions.assertEquals("Welcome to the Vigenère cipher !", VigenereUtils.decrypt(data));
    }

    @Test
    public void testDecryptWithCustomAlphabet() throws VigenereSettingsException {
        final VigenereData data = new VigenereData("AbCdEfGhIjKlprtvym", "MyPrivateKey", "Wdjtopp lo tdi Hpednèly vyjham !".toCharArray());
        Assertions.assertEquals("Welcome to the Vigenère cipher !", VigenereUtils.decrypt(data));
    }

}
