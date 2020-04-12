package fr.oliviermistral.cli;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManifestVersionProviderTest {

    @Test
    public void testGetVersion() throws IOException {
        final ManifestVersionProvider manifestVersionProvider = new ManifestVersionProvider();
        final String[] version = manifestVersionProvider.getVersion();
        Assertions.assertEquals(1, version.length);
        Assertions.assertEquals("x.y.z", version[0]);
    }

}
