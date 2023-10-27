package gr.aueb.cf.springschoolapp.dto.citydto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Data Transfer Object for data needed in
 * update operation of a {@link gr.aueb.cf.springschoolapp.model.City}
 *
 * @author Thanasis Chousiadas
 */
public class CityUpdateDTO {
    private Long id;
    @NotBlank(message = "City name is mandatory")
    @Size(min = 3, max = 45, message = "City's name must be between 3 - 45 characters")
    private String city;

    /**
     * Default constructor.
     */
    public CityUpdateDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id   the id (primary key) of the record to be updated.
     * @param city the city name to be updated
     */
    public CityUpdateDTO(
            Long id,
            String city
    ) {
        this.id = id;
        this.city = city;
    }

    /**
     * Getter for the id name.
     *
     * @return the id of the city.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the city name.
     *
     * @return the city name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the city name.
     *
     * @param city the city name.
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityUpdateDTO{id=" + id + ", city='" + city + '\'' + '}';
    }
}
