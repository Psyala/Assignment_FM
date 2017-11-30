package objects;

import java.util.*;

public class League {
    private final String leagueName;
    private HashMap<String, LeagueTeamInformation> leagueTeamInformation;

    public League(String leagueName) {
        this.leagueName = leagueName;
        leagueTeamInformation = new HashMap<>();
    }

    //Converts a League String back to a League object.
    public static League fromString(String leagueName, String leagueTeamInformationString) {
        List<String> leagueTeamInformationArray = Arrays.asList(leagueTeamInformationString.split("#"));
        HashMap<String, LeagueTeamInformation> hashMap = new HashMap<>();
        for (String leagueTeamInformation : leagueTeamInformationArray) {
            String[] entry = leagueTeamInformation.split(",");
            hashMap.put(entry[0], new LeagueTeamInformation(Integer.valueOf(entry[1])));
        }

        League league = new League(leagueName);
        league.setLeagueTeamInformation(hashMap);
        return league;
    }

    @Override
    //Converts League object to string of #teamCode,Points ...
    public String toString() {
        List<String> leagueTeamInformationArray = new ArrayList<>();
        for (Map.Entry<String, LeagueTeamInformation> entry : leagueTeamInformation.entrySet()) {
            leagueTeamInformationArray.add(entry.getKey() + "," + entry.getValue().getPoints());
        }
        return String.join("#", leagueTeamInformationArray);
    }

    public String getLeagueName() {
        return leagueName;
    }

    public HashMap<String, LeagueTeamInformation> getLeagueTeamInformation() {
        return leagueTeamInformation;
    }

    public void setLeagueTeamInformation(HashMap<String, LeagueTeamInformation> leagueTeamInformation) {
        this.leagueTeamInformation = leagueTeamInformation;
    }

    public static class LeagueTeamInformation {
        private int points;

        public LeagueTeamInformation(int points) {
            this.points = points;
        }

        public int getPoints() {
            return points;
        }

        public void setPoints(int points) {
            this.points = points;
        }
    }
}
