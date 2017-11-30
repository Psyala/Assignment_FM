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

    //Creates a player code of ABC1234
    private String createPlayerCode() {
        return lastName.replace(" ", "").substring(0, 3).toUpperCase() + String.valueOf(((int) (Math.random() * 9000) + 1000));
    }

    //Converts a Player string back to a Player object
    public static Player fromString(String playerString) {
        String[] array = playerString.split("#");
        return new Player(array[0], array[1], array[3], Boolean.valueOf(array[2]));
    }

    @Override
    //Converts Player object to a string of #firstName#lastName#injured#playerCode
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
