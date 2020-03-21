import javax.xml.bind.DatatypeConverter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Classe permettant de déchiffrer un message codé par le chiffrement de Vigenère
 */
public class Vigenere {

    public static void main(final String[] args) {
	    if (args != null && args.length == 3) {
            final String key = hexaToString(args[0]);
            final String alphabet = args[1];
            final String cryptedMsg = args[2];
            final String repeatedKey = generateRepeatedKey(key, cryptedMsg.length());
            System.out.println("Cl\u00e9: " + key);
            System.out.println("Alphabet: " + alphabet);
            System.out.println("Message clair: " + vigenereDecrypt(repeatedKey, alphabet, cryptedMsg));
        } else {
            System.out.println("Bad use ! Syntax : \"key\" \"alphabet\" \"encrypted sequence\"");
        }
    }

    /**
     * Convertit une chaine hexadécimal en chaine de caractères
     *
     * @param hexaString Chaine hexadécimal
     * @return Chaine de caractère
     */
    public static String hexaToString(final String hexaString) {
        return new String(DatatypeConverter.parseHexBinary(hexaString));
    }

    /**
     * Génère la clé répétée
     *
     * @param key Clé
     * @param msgLength Taille du message codé
     * @return La clé répétée
     */
    public static String generateRepeatedKey(final String key, final int msgLength) {
        final StringBuilder repeatedKey = new StringBuilder();
        while (repeatedKey.length() < msgLength) {
            repeatedKey.append(key);
        }
        repeatedKey.setLength(msgLength);
        return repeatedKey.toString();
    }

    /**
     * Décrypte un message codé avec le chiffrement de Vigenère
     *
     * @param repeatedKey Clé répété
     * @param alphabet Alphabet pour le décryptage
     * @param cryptedMsg Message encrypté
     * @return Message décrypté
     */
    public static String vigenereDecrypt(final String repeatedKey, final String alphabet, final String cryptedMsg) {
        final StringBuilder result = new StringBuilder();
        Map<Character, Map<Character, Character>> square = new HashMap<Character, Map<Character, Character>>();
        final int nbAlphaEl = alphabet.length();
        // Construction du carré de Vigenère
        for (int i = 0; i < nbAlphaEl; i++) {
            // On ne récupère que les colonnes qui nous intéressent, à savoir celles de la clé
            if (repeatedKey.contains(String.valueOf(alphabet.charAt(i)))) {
                final Map<Character, Character> column = new HashMap<>();
                for (int j = 0; j < nbAlphaEl; j++) {
                    column.put(alphabet.charAt(j), alphabet.charAt((j + i) % nbAlphaEl));
                }
                square.put(alphabet.charAt(i), column);
            }
        }
        // Décryptage
        for (int i = 0; i < cryptedMsg.length(); i++) {
            Set<Character> lines = square.get(repeatedKey.charAt(i)).keySet();
            for (final Character line : lines) {
                if (square.get(repeatedKey.charAt(i)).get(line) == cryptedMsg.charAt(i)) {
                    result.append(line);
                    break;
                }
            }
        }
        return result.toString();
    }

}
