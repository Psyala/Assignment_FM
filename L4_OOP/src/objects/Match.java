package objects;

import java.text.SimpleDateFormat;
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

    //Creates gameCode in the form game (homeTeamCode + "v" + awayTeamCode + Date("dd/MM/yy")
    private String createGameCode() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        return homeTeam.getTeamCode() + "v" + awayTeam.getTeamCode() + formatter.format(dateOfMatch);
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

    public String getGameCode() {
        return gameCode;
    }

    public Venue getVenue() {
        return venue;
    }

    public Date getDateOfMatch() {
        return dateOfMatch;
    }
}
