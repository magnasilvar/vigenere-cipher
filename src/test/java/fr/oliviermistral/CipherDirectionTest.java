package fr.oliviermistral;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CipherDirectionTest {

    @Test
    public void testFromNull() {
        Assertions.assertNull(CipherDirection.from(null));
    }

    @Test
    public void testFromUnknownValue() {
        Assertions.assertNull(CipherDirection.from("Unknown"));
    }

    @Test
    public void testFromDirection() {
        Assertions.assertEquals(CipherDirection.ENCRYPT, CipherDirection.from(CipherDirection.ENCRYPT.getDirection()));
    }

}
