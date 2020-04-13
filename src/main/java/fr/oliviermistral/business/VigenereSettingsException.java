package fr.oliviermistral.business;

import java.util.Collection;
import java.util.stream.Collectors;

public class VigenereSettingsException extends Exception {

    private static final long serialVersionUID = 3902254503872794446L;

    public VigenereSettingsException(final Collection<String> errors) {
        super(new StringBuilder().append("Error").append(errors.size() == 1 ? "" : "s").append(":")
                .append(System.lineSeparator())
                .append(errors.stream().map(e -> "- " + e).collect(Collectors.joining(System.lineSeparator())))
                .toString());
    }

}
