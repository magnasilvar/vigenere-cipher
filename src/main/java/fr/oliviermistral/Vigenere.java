package fr.oliviermistral;

import java.util.List;

/**
 * Classe permettant de déchiffrer un message codé par le chiffrement de
 * Vigenère
 */
public class Vigenere {

    public static void main(final String[] args) {
        if (!List.of(3, 4).contains(args.length)) {
            System.out.println("Bad use ! Syntax : \"" + CipherDirection.ENCRYPT.getDirection() + "|"
                    + CipherDirection.DECRYPT.getDirection()
                    + "\" \"Text to encrypt / decrypt\" \"key\" \"(optional) alphabet\"");
            return;
        }
        final String alphabet = args.length == 4 ? args[3] : null;
        try {
            final VigenereData data = new VigenereData(args[0], alphabet, args[2], args[1].toCharArray());
            System.out.println("key=" + data.getKey());
            System.out.println("alphabet=" + data.getAlphabet());
            System.out.println(VigenereUtils.cipher(data));
        } catch (final VigenereSettingsException e) {
            System.err.println(e.getMessage());
        }
    }

}
