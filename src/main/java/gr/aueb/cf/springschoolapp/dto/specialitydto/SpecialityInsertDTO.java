package gr.aueb.cf.springschoolapp.dto.specialitydto;


/**
 * The Data Transfer Object for data needed in
 * insert operation of a {@link gr.aueb.cf.springschoolapp.model.Speciality}
 *
 * @author Thanasis Chousiadas
 */
public class SpecialityInsertDTO {
    private String specialityName;

    /**
     * Default constructor.
     */
    public SpecialityInsertDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param specialityName the speciality to be inserted
     */
    public SpecialityInsertDTO(String specialityName) {
        this.specialityName = specialityName;
    }

    /**
     * Getter for the speciality.
     *
     * @return the speciality.
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * Setter for the speciality.
     *
     * @param specialityName the speciality.
     */
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
