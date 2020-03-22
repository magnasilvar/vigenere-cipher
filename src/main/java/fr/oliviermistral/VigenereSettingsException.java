package fr.oliviermistral;

import java.util.Collection;
import java.util.stream.Collectors;

public class VigenereSettingsException extends Exception {

    private static final long serialVersionUID = 7982938519709995493L;

    public VigenereSettingsException(final Collection<String> errors) {
        super(new StringBuilder()
            .append("Settings errors:")
            .append(System.lineSeparator())
            .append(errors.stream().map(e -> "- " + e).collect(Collectors.joining(System.lineSeparator())))
            .toString());
    }

}
