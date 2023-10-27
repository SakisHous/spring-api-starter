package gr.aueb.cf.springschoolapp.model;

/**
 * Enum type for the {@link Student} gender field.
 * It has two values M and F with their
 * corresponding labels.
 *
 * @author Thanasis Chousiadas
 */
public enum Gender {
    M("MALE"),
    F("FEMALE");

    private String label;

    /**
     * Private constructor. Automatically creates the constants that are defined.
     *
     * @param label the name of the label.
     */
    private Gender(String label) {
        this.label = label;
    }

    /**
     * Getter for the label of the value.
     * This string is stored in the database.
     *
     * @return the label for the value.
     */
    public String getLabel() {
        return this.label;
    }
}
