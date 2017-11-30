package objects;

public class Venue {
    private String venueName;
    private final String venueCode;

    public Venue(String venueName) {
        this.venueName = venueName;
        venueCode = createVenueCode();
    }

    public Venue(String venueName, String venueCode) {
        this.venueName = venueName;
        this.venueCode = venueCode;
    }

    //Converts a Venue string back into a Venue object
    public static Venue fromString(String venueString) {
        if (venueString.isEmpty()) {
            return null;
        }
        String[] array = venueString.split("#");
        return new Venue(array[1], array[0]);
    }

    @Override
    //Converts Venue object to string of venueCode#venueName
    public String toString() {
        return venueCode + "#" + venueName;
    }

    //Creates a venue code of ABC12
    private String createVenueCode() {
        return venueName.replace(" ", "").substring(0, 3).toUpperCase() + String.valueOf(((int) (Math.random() * 90) + 10));
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueCode() {
        return venueCode;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }
}
