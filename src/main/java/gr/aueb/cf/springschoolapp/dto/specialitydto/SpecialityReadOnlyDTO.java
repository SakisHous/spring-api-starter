package gr.aueb.cf.springschoolapp.dto.specialitydto;

/**
 * The Data Transfer Object for data needed of a {@link gr.aueb.cf.springschoolapp.model.Speciality}
 * when passing the data for displaying them to the client.
 *
 * @author Thanasis Chousiadas
 */
public class SpecialityReadOnlyDTO {
    private Long id;
    private String name;

    /**
     * Default constructor.
     */
    public SpecialityReadOnlyDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id the id of the speciality.
     * @param name the name of the speciality.
     */
    public SpecialityReadOnlyDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Getter for the id of the speciality.
     *
     * @return the id of speciality.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the speciality.
     *
     * @param id the id of the speciality.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the name of the speciality.
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the speciality.
     *
     * @param name the name of the speciality.
     */
    public void setName(String name) {
        this.name = name;
    }
}
