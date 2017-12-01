package storage;

import objects.Match;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchStorage extends StorageRetriever {
    //Stores list of matches into properties
    private static void storeMatchesList(List<String> matchCodeList) {
        String stringList = String.join(",", matchCodeList);
        StorageRetriever.setPropertyValue("matchesList", stringList);
    }

    //Adds a player to the list of players
    private static void addMatchToList(Match match) {
        String matchCode = match.getMatchCode();
        List<String> list = getMatchesList();
        if (!list.contains(matchCode)) {
            list.add(matchCode);
            storeMatchesList(list);
        }
    }

    //Gets the list of matches from properties
    public static List<String> getMatchesList() {
        String matches = getPropertyValue("matchesList", "");
        if (matches.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(matches.split(",")));
        }
    }

    //Gets a single match from properties by its match code
    public static Match getMatch(String matchCode) {
        String matchString = StorageRetriever.getPropertyValue(matchCode, "");
        if (matchString.isEmpty()) {
            return null;
        } else {
            return Match.fromString(matchString);
        }
    }

    //Store a single match into properties by its match code
    public static void storeMatch(Match match) {
        StorageRetriever.setPropertyValue(match.getMatchCode(), match.toString());
        addMatchToList(match);
    }

    //Retireve a list of all matches from properties
    public static List<Match> retrieveMatches() {
        List<Match> matchList = new ArrayList<>();
        List<String> matchCodeList = getMatchesList();

        for (String playerCode : matchCodeList) {
            Match match = getMatch(playerCode);
            matchList.add(match);
        }

        return matchList;
    }
}
