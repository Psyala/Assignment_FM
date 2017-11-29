package objects;

import objects.base.Person;

public class Player extends Person {
    private final String playerCode;
    private boolean injured = false;

    public Player(String firstName, String lastName) {
        super(firstName, lastName);
        playerCode = createPlayerCode();
    }

    public Player(String firstName, String lastName, String playerCode, boolean injured) {
        super(firstName, lastName);
        this.playerCode = playerCode;
        this.injured = injured;
    }

    private String createPlayerCode() {
        return lastName.substring(0, 3).toUpperCase() + String.valueOf(((int) (Math.random() * 9000) + 1000));
    }

    public static Player fromString(String playerString) {
        String[] array = playerString.split("#");
        return new Player(array[0], array[1], array[3], Boolean.valueOf(array[2]));
    }

    @Override
    public String toString() {
        return firstName + "#" + lastName + "#" + injured + "#" + playerCode;
    }

    public String getPlayerCode() {
        return playerCode;
    }

    public boolean getInjured() {
        return injured;
    }

    public void setInjured(boolean injured) {
        this.injured = injured;
    }
}
