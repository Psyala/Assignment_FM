package storage;

import objects.League;

public class LeagueStorage extends StorageRetriever {
    //Has a league property object been created
    public static boolean doesLeagueExist() {
        return !getLeagueName().isEmpty();
    }

    //Retrieves the name of the stored league
    public static String getLeagueName() {
        return StorageRetriever.getPropertyValue("leagueName", "");
    }

    //Sets the name of the league property
    public static void setLeagueName(String leagueName) {
        StorageRetriever.setPropertyValue("leagueName", leagueName);
    }

    //Gets the leagueObject from properties to create a League object
    public static League getLeagueObject() {
        String leagueString = StorageRetriever.getPropertyValue("leagueObject", "");
        if (leagueString.isEmpty()) {
            return new League(getLeagueName());
        } else {
            return League.fromString(getLeagueName(), leagueString);
        }
    }

    //Stores League object into properties
    public static void storeLeagueObject(League league) {
        StorageRetriever.setPropertyValue("leagueObject", league.toString());
    }
}
