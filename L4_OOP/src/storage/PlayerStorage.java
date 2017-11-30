package storage;

import objects.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerStorage extends StorageRetriever {
    //Stores list of players into properties
    private static void storePlayersList(List<String> playersList) {
        String stringList = String.join(",", playersList);
        StorageRetriever.setPropertyValue("playerList", stringList);
    }

    //Adds a player to the list of players
    private static void addPlayerToList(Player player) {
        String playerCode = player.getPlayerCode();
        List<String> list = getPlayerList();
        if (!list.contains(playerCode)) {
            list.add(playerCode);
            storePlayersList(list);
        }
    }

    //Gets the list of players from properties
    public static List<String> getPlayerList() {
        String players = getPropertyValue("playerList", "");
        if (players.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(players.split(",")));
        }
    }

    //Gets a single player from properties by their playercode
    public static Player getPlayer(String playerCode) {
        String playerString = StorageRetriever.getPropertyValue(playerCode, "");
        if (playerString.isEmpty()) {
            return null;
        } else {
            return Player.fromString(playerString);
        }
    }

    //Store a single player into properties by their playercode
    public static void storePlayer(Player player) {
        StorageRetriever.setPropertyValue(player.getPlayerCode(), player.toString());
        addPlayerToList(player);
    }

    //Retireve a list of all players from properties
    public static List<Player> retrievePlayers() {
        List<Player> playerList = new ArrayList<>();
        List<String> playerCodeList = getPlayerList();

        for (String playerCode : playerCodeList) {
            Player player = getPlayer(playerCode);
            playerList.add(player);
        }

        return playerList;
    }
}
