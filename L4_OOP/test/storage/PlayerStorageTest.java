package storage;

import objects.Player;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerStorageTest {
    Player player1;

    @Test
    void retrievePlayerList() {
        player1 = Player.fromString("Callum#Vernon#false#VER9568");

        List<String> playerList = PlayerStorage.getPlayerList();
        if (!playerList.contains("VER9568")) {
            PlayerStorage.storePlayer(player1);
        }

        Player returnedPlayer = PlayerStorage.getPlayer("VER9568");

        assertEquals(player1.toString(), returnedPlayer.toString());
    }
}