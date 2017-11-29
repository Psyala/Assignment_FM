package objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamTest {
    private Team createTestObject() {
        Team testTeam = new Team("Thornaby FC");
        return  testTeam;
    }

    @Test
    void createTeamCode() {
        Team testTeam = createTestObject();
        assertTrue(testTeam.getTeamCode().matches("[0-9]{3}THO"));
    }

}