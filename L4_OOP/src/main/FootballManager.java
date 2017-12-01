package main;

import forms.MainForm;
import forms.NewLeagueForm;
import objects.*;
import storage.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class FootballManager {
    public static final String APP_TITLE = "Football Manager - CLV";
    public static List<Player> playerList;
    public static List<Team> teamList;
    public static List<Venue> venueList;
    public static List<Match> matchList;
    public static String leagueName;
    public static League leagueObject;
    public static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
    private static MainForm mainForm = null;

    //Initialises the public static variables for use in the application elsewhere
    public static void initialiseData() {
        playerList = PlayerStorage.retrievePlayers();
        teamList = TeamStorage.retrieveTeams();
        venueList = VenueStorage.retrieveVenues();
        leagueName = LeagueStorage.getLeagueName();
        leagueObject = populateLeagueObject();
        matchList = MatchStorage.retrieveMatches();

        if (mainForm != null) {
            mainForm.populateLeagueStatistics();
            mainForm.populateMatches();
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
