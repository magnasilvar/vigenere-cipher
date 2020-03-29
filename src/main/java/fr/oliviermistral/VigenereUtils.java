package fr.oliviermistral;

import java.util.function.IntFunction;

public final class VigenereUtils {

    private VigenereUtils() {
        // All methods are static
    }

    public static String cipher(final VigenereData data) {
        final StringBuilder result = new StringBuilder();
        final IntFunction<IntFunction<IntFunction<Integer>>> findLetterPosition;
        if (data.getCipherDirection() == CipherDirection.ENCRYPT) {
            findLetterPosition = findEncryptedLetterPosition();
            result.append("Cipher text: ");
        } else {
            findLetterPosition = findDecryptedLetterPosition();
            result.append("Plain text: ");
        }
        return cipher(result, data, findLetterPosition);
    }

    private static String cipher(final StringBuilder result, final VigenereData data, final IntFunction<IntFunction<IntFunction<Integer>>> findLetterPosition) {
        int i = 0;
        for (final char c : data.getChars()) {
            boolean isLowerCase = false;
            int posChar = data.getAlphabet().indexOf(c);
            if (posChar < 0) {
                posChar = data.getAlphabet().indexOf(Character.toUpperCase(c));
                if (posChar < 0) {
                    result.append(c);
                    continue;
                } else {
                    isLowerCase = true;
                }
            }
            final int posKey = data.getAlphabet().indexOf(data.getKey().charAt(i % data.getKeyLength()));
            final int alphabetIndex = findLetterPosition.apply(posKey).apply(posChar).apply(data.getAlphabetLength());
            final char letter = data.getAlphabet().charAt(alphabetIndex);
            result.append(isLowerCase ? Character.toLowerCase(letter) : letter);
            i++;
        }
        return result.toString();
    }

    private static IntFunction<IntFunction<IntFunction<Integer>>> findEncryptedLetterPosition() {
        return posKey -> (posLetter -> (alphabetLength -> (posLetter + posKey) % alphabetLength));
    }

    private static IntFunction<IntFunction<IntFunction<Integer>>> findDecryptedLetterPosition() {
        return posKey -> (posLetter -> (alphabetLength -> {
            final int diff = posLetter - posKey;
            return diff < 0 ? diff + alphabetLength : diff;
        }));
    }

}
