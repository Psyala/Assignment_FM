package objects;

public class ValidationObject {
    private final boolean valid;
    private final String validationMessage;

    public ValidationObject(boolean valid, String validationMessage) {
        this.valid = valid;
        this.validationMessage = validationMessage;
    }

    //If the object being validated passed or not
    public boolean isValid() {
        return valid;
    }

    //Validation message to show if isValid is false
    public String getValidationMessage() {
        return validationMessage;
    }
}
