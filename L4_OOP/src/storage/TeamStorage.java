package storage;

import objects.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TeamStorage extends StorageRetriever {
    //Stores list of teams into properties
    private static void storeTeamsList(List<String> teamsList) {
        String stringList = String.join(",", teamsList);
        StorageRetriever.setPropertyValue("teamsList", stringList);
    }

    //Adds a team into the team list
    private static void addPlayerToList(Team team) {
        String teamCode = team.getTeamCode();
        List<String> list = getTeamsList();
        if (!list.contains(teamCode)) {
            list.add(teamCode);
            storeTeamsList(list);
        }
    }

    //Retreive the team list from properties
    public static List<String> getTeamsList() {
        String teams = getPropertyValue("teamsList", "");
        if (teams.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(teams.split(",")));
        }
    }

    //Get a singular team from properties by teamCode
    public static Team getTeam(String teamCode) {
        String teamString = StorageRetriever.getPropertyValue(teamCode, "");
        if (teamString.isEmpty()) {
            return null;
        } else {
            return Team.fromString(teamString);
        }
    }

    //Store a singular team into properties by teamCode
    public static void storeTeam(Team team) {
        StorageRetriever.setPropertyValue(team.getTeamCode(), team.toString());
        addPlayerToList(team);
    }

    //Retrieve a list of all Teams from properties
    public static List<Team> retrieveTeams() {
        List<Team> teamList = new ArrayList<>();
        List<String> teamCodeList = getTeamsList();

        for (String teamCode : teamCodeList) {
            Team team = getTeam(teamCode);
            teamList.add(team);
        }

        return teamList;
    }
}
