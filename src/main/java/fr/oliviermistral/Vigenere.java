package fr.oliviermistral;

import java.util.List;

/**
 * Classe permettant de déchiffrer un message codé par le chiffrement de
 * Vigenère
 */
public class Vigenere {

    public static void main(final String[] args) {
        if (!List.of(2, 3).contains(args.length)) {
            System.out.println("Bad use ! Syntax : \"encrypted sequence\" \"key\" \"alphabet (optional)\"");
            return;
        }
        final String alphabet = args.length == 3 ? args[2] : null;
        try {
            final VigenereData data = new VigenereData(alphabet, args[1], args[0].toCharArray());
            System.out.println("key=" + data.getKey());
            System.out.println("alphabet=" + data.getAlphabet());
            System.out.println("Decrypted sequence: " + VigenereUtils.decrypt(data));
        } catch (final VigenereSettingsException e) {
            System.err.println(e.getMessage());
        }
    }

}
