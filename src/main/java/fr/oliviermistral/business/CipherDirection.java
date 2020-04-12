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

}
