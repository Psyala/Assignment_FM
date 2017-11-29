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

    public static Venue fromString(String venueString) {
        if (venueString.isEmpty()) {
            return null;
        }
        String[] array = venueString.split("#");
        return new Venue(array[1], array[0]);
    }

    @Override
    public String toString() {
        return venueCode + "#" + venueName;
    }

    private String createVenueCode() {
        return venueName.substring(0, 3).toUpperCase() + String.valueOf(((int) (Math.random() * 90) + 10));
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
