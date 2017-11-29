package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player createTestObject() {
        Player testPlayer = new Player("Callum", "Vernon");
        return  testPlayer;
    }

    @Test
    public void createPlayerCode() {
        Player testPlayer = createTestObject();
        assertTrue(testPlayer.getPlayerCode().matches("VER[0-9]{4}"));
    }

}