package main;

import objects.Player;
import objects.Team;
import objects.Venue;
import storage.PlayerStorage;
import storage.TeamStorage;
import storage.VenueStorage;

import java.util.List;

public class FootballManager {
    public static final String APP_TITLE = "Football Manager - CLV";
    public static List<Player> playerList;
    public static List<Team> teamList;
    public static List<Venue> venueList;

    public static void populateLists() {
        playerList = PlayerStorage.retrievePlayers();
        teamList = TeamStorage.retrieveTeams();
        venueList = VenueStorage.retrieveVenues();
    }

    public static void main(String[] args) {
        populateLists();
        //PlayerForm playerForm = new PlayerForm(null);
        //VenueForm venueForm = new VenueForm(null);
        //TeamForm teamForm = new TeamForm(TeamStorage.getTeam("947THO"));
    }
}
