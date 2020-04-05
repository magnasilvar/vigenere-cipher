package fr.oliviermistral.business;

public enum CipherDirection {

    ENCRYPT("encrypt"), DECRYPT("decrypt");

    private String direction;

    CipherDirection(final String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public static CipherDirection from(final String value) {
        for (final CipherDirection cipherDirection : CipherDirection.values()) {
            if (cipherDirection.getDirection().equals(value)) {
                return cipherDirection;
            }
        }
        return null;
    }

}
