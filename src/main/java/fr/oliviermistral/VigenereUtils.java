package fr.oliviermistral;

public final class VigenereUtils {

    private VigenereUtils() {
        // All methods are static
    }

    public static String decrypt(final VigenereData data) {
        StringBuilder decryptedMessage = new StringBuilder();
        int i = 0;
        for (final char c : data.getChars()) {
            boolean isLowerCase = false;
            int posChar = data.getAlphabet().indexOf(c);
            if (posChar < 0) {
                posChar = data.getAlphabet().indexOf(Character.toUpperCase(c));
                if (posChar < 0) {
                    decryptedMessage.append(c);
                    continue;
                } else {
                    isLowerCase = true;
                }
            }
            final int posKey = data.getAlphabet().indexOf(data.getKey().charAt(i % data.getKeyLength()));
            final int diff = posChar - posKey;
            final int alphabetIndex = diff < 0 ? diff + data.getAlphabetLength() : diff;
            final char decrypted = data.getAlphabet().charAt(alphabetIndex);
            decryptedMessage.append(isLowerCase ? Character.toLowerCase(decrypted) : decrypted);
            i++;
        }
        return decryptedMessage.toString();
    }

}
