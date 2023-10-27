package gr.aueb.cf.springschoolapp.dto.specialitydto;


/**
 * The Data Transfer Object for data needed in
 * insert operation of a {@link gr.aueb.cf.springschoolapp.model.Speciality}
 *
 * @author Thanasis Chousiadas
 */
public class SpecialityUpdateDTO {
    private Long id;
    private String specialityName;

    /**
     * Default constructor.
     */
    public SpecialityUpdateDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id         the primary key for the record to be updated.
     * @param specialityName the new speciality.
     */
    public SpecialityUpdateDTO(Long id, String specialityName) {
        this.id = id;
        this.specialityName = specialityName;
    }

    /**
     * Getter for the id.
     *
     * @return Long id.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the speciality.
     *
     * @return the speciality name.
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * Setter for the speciality.
     *
     * @param specialityName the speciality name.
     */
    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }
}
