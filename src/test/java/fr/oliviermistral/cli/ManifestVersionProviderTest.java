package fr.oliviermistral.cli;

import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ManifestVersionProviderTest {

    @Test
    public void testGetVersionWithoutConfig() throws IOException {
        final ManifestVersionProvider manifestVersionProvider = new ManifestVersionProvider();
        Assertions.assertEquals(0, manifestVersionProvider.getVersion().length);
    }

}
