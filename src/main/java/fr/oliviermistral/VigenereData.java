package fr.oliviermistral;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class VigenereData {

    protected static final String DEFAULT_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final int keyLength;
    private final String key;
    private final int alphabetLength;
    private final String alphabet;
    private final char[] chars;
    private final List<String> errors = new ArrayList<>();

    public VigenereData(final String alphabet, final String key, final char[] chars) throws VigenereSettingsException {
        this.alphabet = manageAlphabet(alphabet);
        this.alphabetLength = this.alphabet.length();
        this.key = manageKey(key);
        this.keyLength = this.key.length();
        this.chars = chars;
    
        if (!errors.isEmpty()) {
            throw new VigenereSettingsException(errors);
        }
    }

    public int getKeyLength() {
        return keyLength;
    }

    public String getKey() {
        return key;
    }

    public int getAlphabetLength() {
        return alphabetLength;
    }

    public String getAlphabet() {
        return alphabet;
    }

    public char[] getChars() {
        return chars;
    }

    private String manageAlphabet(final String alphabet) {
        if (alphabet == null) {
            return DEFAULT_ALPHABET;
        }
        final List<Character> alphabetChars = alphabet.toUpperCase().chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        final Set<Character> duplicates =
            alphabetChars.stream().filter(ac -> Collections.frequency(alphabetChars, ac) > 1).collect(Collectors.toSet());
        if (!duplicates.isEmpty()) {
            errors.add("Alphabet contains non unique characters: " + duplicates);
        }
        return alphabet.toUpperCase();
    }

    private String manageKey(final String key) {
        final String upperKey = key.toUpperCase();
        final Set<Character> forbiddeChars = upperKey.chars().mapToObj(c -> (char) c)
            .filter(c -> !alphabet.contains(String.valueOf(c))).collect(Collectors.toSet());
        if (!forbiddeChars.isEmpty()) {
            errors.add("Key contains forbidden characters (not included in the alphabet): " + forbiddeChars);
        }
        return upperKey;
    }

}
