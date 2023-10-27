package gr.aueb.cf.springschoolapp.dto.citydto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Data Transfer Object for data needed in
 * insert operation of a {@link gr.aueb.cf.springschoolapp.model.City}
 *
 * @author Thanasis Chousiadas
 */
public class CityInsertDTO {
    @NotBlank(message = "City name is mandatory")
    @Size(min = 3, max = 45, message = "City's name must be between 3 - 45 characters")
    private String city;

    /**
     * Default constructor
     */
    public CityInsertDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param city the name of the city.
     */
    public CityInsertDTO(String city) {
        this.city = city;
    }

    /**
     * Getter for the name of the city.
     *
     * @return a String with the name.
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for the name of the city.
     *
     * @param city the name of the city.
     */
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "CityInsertDTO{city='" + city + '\'' + '}';
    }
}
