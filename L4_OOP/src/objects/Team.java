package objects;

import storage.PlayerStorage;
import storage.VenueStorage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Team {
    private final String teamName;
    private final String teamCode;
    private Venue venue;
    List<Player> players = new ArrayList<>();

    public Team(String teamName) {
        this.teamName = teamName;
        teamCode = createTeamCode();
    }

    public Team(String teamName, String teamCode, List<Player> players, Venue venue) {
        this.teamName = teamName;
        this.teamCode = teamCode;
        this.players = players;
        this.venue = venue;
    }

    //Converts a Team string back to a Team object
    public static Team fromString(String teamString) {
        String[] array = teamString.split("#");

        List<Player> players = new ArrayList<>();
        List<String> playerCodes = Arrays.asList(array[2].split(","));
        for (String playerCode : playerCodes) {
            players.add(PlayerStorage.getPlayer(playerCode));
        }

        Venue venue = VenueStorage.getVenue(array[3]);

        return new Team(array[0], array[1], players, venue);
    }

    @Override
    //Converts Team object to a string of teamName#teamCode#playerList#venueCode
    public String toString() {
        List<String> playerCodes = new ArrayList<>();
        for (Player player : players) {
            playerCodes.add(player.getPlayerCode());
        }
        String playerList = String.join(",", playerCodes);
        return teamName + "#" + teamCode + "#" + playerList + "#" + venue.getVenueCode();
    }

    //Creates team code of 123ABC
    private String createTeamCode() {
        return String.valueOf((int) (Math.random() * 900) + 100) + teamName.replace(" ", "").substring(0, 3).toUpperCase();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public String getTeamName() {
        return teamName;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }
}
