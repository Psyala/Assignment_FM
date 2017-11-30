package main;

import forms.MainForm;
import forms.NewLeagueForm;
import objects.League;
import objects.Player;
import objects.Team;
import objects.Venue;
import storage.LeagueStorage;
import storage.PlayerStorage;
import storage.TeamStorage;
import storage.VenueStorage;

import java.util.HashMap;
import java.util.List;

public class FootballManager {
    public static final String APP_TITLE = "Football Manager - CLV";
    public static List<Player> playerList;
    public static List<Team> teamList;
    public static List<Venue> venueList;
    public static String leagueName;
    public static League leagueObject;
    private static MainForm mainForm = null;

    //Initialises the public static variables for use in the application elsewhere
    public static void initialiseData() {
        playerList = PlayerStorage.retrievePlayers();
        teamList = TeamStorage.retrieveTeams();
        venueList = VenueStorage.retrieveVenues();
        leagueName = LeagueStorage.getLeagueName();
        leagueObject = populateLeagueObject();
        if (mainForm != null) {
            mainForm.populateLeagueStatistics();
        }
    }

    //Loads the mainform
    public static void load() {
        initialiseData();
        mainForm = new MainForm();
    }

    public static void main(String[] args) {
        if (LeagueStorage.doesLeagueExist()) {
            load();
        } else {
            new NewLeagueForm();
        }
    }

    //Populates the LeagueObject that is public static for elsewhere to use
    private static League populateLeagueObject() {
        League league = LeagueStorage.getLeagueObject();
        HashMap<String, League.LeagueTeamInformation> leagueTeamInformation = league.getLeagueTeamInformation();
        HashMap<String, League.LeagueTeamInformation> newLeagueTeamInformation = new HashMap<>();

        for (Team team : teamList) {
            int points = 0;
            if (leagueTeamInformation.containsKey(team.getTeamCode())) {
                points = leagueTeamInformation.get(team.getTeamCode()).getPoints();
            }
            newLeagueTeamInformation.put(team.getTeamCode(), new League.LeagueTeamInformation(points));
        }

        league.setLeagueTeamInformation(newLeagueTeamInformation);

        LeagueStorage.storeLeagueObject(league);
        return league;
    }
}
