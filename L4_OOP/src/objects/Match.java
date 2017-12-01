package objects;

import main.FootballManager;
import storage.TeamStorage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Match {
    private final Team homeTeam;
    private final Team awayTeam;
    private final String gameCode;
    private final Venue venue;
    private final Date dateOfMatch;
    private List<Player> homePlayers;
    private List<Player> awayPlayers;

    public Match(Team homeTeam, List<Player> homePlayers, Team awayTeam, List<Player> awayPlayers, Date dateOfMatch) {
        this.homeTeam = homeTeam;
        this.homePlayers = homePlayers;
        this.awayTeam = awayTeam;
        this.awayPlayers = awayPlayers;
        this.dateOfMatch = dateOfMatch;
        venue = homeTeam.getVenue();
        gameCode = createGameCode();
    }

    public Match(Team homeTeam, List<Player> homePlayers, Team awayTeam, List<Player> awayPlayers, Date dateOfMatch, String gameCode) {
        this.homeTeam = homeTeam;
        this.homePlayers = homePlayers;
        this.awayTeam = awayTeam;
        this.awayPlayers = awayPlayers;
        this.dateOfMatch = dateOfMatch;
        venue = homeTeam.getVenue();
        this.gameCode = gameCode;
    }

    //Creates gameCode in the form game (homeTeamCode + "v" + awayTeamCode + Date("ddMMyy")
    private String createGameCode() {
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
        return homeTeam.getTeamCode() + "v" + awayTeam.getTeamCode() + formatter.format(dateOfMatch);
    }

    //Creates a Match object from Match String
    public static Match fromString(String matchString) {
        try {
            List<Player> blankPlayersList = new ArrayList<>(); //TODO Needs to be implemented

            String[] array = matchString.split("#");
            String gameCode = array[0];
            Team homeTeam = TeamStorage.getTeam(array[1]);
            Team awayTeam = TeamStorage.getTeam(array[2]);
            Date dateOfMatch = FootballManager.formatter.parse(array[3]);

            return new Match(homeTeam, blankPlayersList, awayTeam, blankPlayersList, dateOfMatch, gameCode);
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    @Override
    //Converts Match Object to Match String
    public String toString() {
        return gameCode + "#" + homeTeam.getTeamCode() + "#" + awayTeam.getTeamCode() + "#" + FootballManager.formatter.format(dateOfMatch);
    }

    public List<Player> getHomePlayers() {
        return homePlayers;
    }

    public void setHomePlayers(List<Player> homePlayers) {
        this.homePlayers = homePlayers;
    }

    public List<Player> getAwayPlayers() {
        return awayPlayers;
    }

    public void setAwayPlayers(List<Player> awayPlayers) {
        this.awayPlayers = awayPlayers;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public String getMatchCode() {
        return gameCode;
    }

    public Venue getVenue() {
        return venue;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }
}
