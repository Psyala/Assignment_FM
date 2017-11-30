package storage;

import objects.Venue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VenueStorage extends StorageRetriever {
    //Stores the venue list into properties
    private static void storeVenuesList(List<String> venuesList) {
        String stringList = String.join(",", venuesList);
        StorageRetriever.setPropertyValue("venuesList", stringList);
    }

    //Add a venue to the venue list
    private static void addVenueToList(Venue venue) {
        String venueCode = venue.getVenueCode();
        List<String> list = getVenuesList();
        if (!list.contains(venueCode)) {
            list.add(venueCode);
            storeVenuesList(list);
        }
    }

    //Gets the venue list from properties
    public static List<String> getVenuesList() {
        String venues = getPropertyValue("venuesList", "");
        if (venues.isEmpty()) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(venues.split(",")));
        }
    }

    //Gets a singular venue from properties
    public static Venue getVenue(String venueCode) {
        String venueString = StorageRetriever.getPropertyValue(venueCode, "");
        if (venueString.isEmpty()) {
            return null;
        } else {
            return Venue.fromString(venueString);
        }
    }

    //Stores a singular venue into the properties
    public static void storeVenue(Venue venue) {
        StorageRetriever.setPropertyValue(venue.getVenueCode(), venue.toString());
        addVenueToList(venue);
    }

    //Retrieves a list of all Venues from properties
    public static List<Venue> retrieveVenues() {
        List<Venue> venueList = new ArrayList<>();
        List<String> venueCodeList = getVenuesList();

        for (String venueCode : venueCodeList) {
            Venue venue = getVenue(venueCode);
            venueList.add(venue);
        }

        return venueList;
    }
}
