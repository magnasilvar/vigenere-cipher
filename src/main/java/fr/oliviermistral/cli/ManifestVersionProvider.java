package fr.oliviermistral.cli;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import picocli.CommandLine;
import picocli.CommandLine.IVersionProvider;

public final class ManifestVersionProvider implements IVersionProvider {

    @Override
    public String[] getVersion() throws IOException {
        final Enumeration<URL> resources = CommandLine.class.getClassLoader().getResources("META-INF/MANIFEST.MF");
        while (resources.hasMoreElements()) {
            final URL url = resources.nextElement();
            try (final InputStream urlStream = url.openStream()) {
                final Attributes attributes = new Manifest(urlStream).getMainAttributes();
                if (isApplicableManifest(attributes)) {
                    return new String[] { attributes.getValue("Implementation-Version") };
                }
            } catch (final IOException ex) {
                return new String[] { "Unable to read from " + url + ": " + ex };
            }
        }
        return new String[0];
    }

    private boolean isApplicableManifest(final Attributes attributes) {
        return "vigenere-cipher".equals(attributes.getValue("Implementation-Title"));
    }

}
