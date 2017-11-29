package objects;

public class ValidationObject {
    private final boolean valid;
    private final String validationMessage;

    public ValidationObject(boolean valid, String validationMessage) {
        this.valid = valid;
        this.validationMessage = validationMessage;
    }

    public boolean isValid() {
        return valid;
    }

    public String getValidationMessage() {
        return validationMessage;
    }
}
