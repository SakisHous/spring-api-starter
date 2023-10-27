package gr.aueb.cf.springschoolapp.dto.studentdto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Data Transfer Object for data needed in update
 * operation of a {@link gr.aueb.cf.springschoolapp.model.Student}
 *
 * @author Thanasis Chousiadas
 */
public class StudentUpdateDTO {
    private Long id;
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 52, message = "Firstname must be between 9 - 52 characters")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 52, message = "Lastname must be between 9 - 52 characters")
    private String lastname;
    @Size(min = 1, max = 1, message = "Gender must be F (Female) or M (Male)")
    private String gender;
    private String birthdate;
    private String city;
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * Default constructor.
     */
    public StudentUpdateDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id        the id (primary key) is needed for update the record.
     * @param firstname the firstname of the student.
     * @param lastname  the lastname of the student.
     * @param gender    the gender of the student.
     * @param birthdate the birthdate of the student.
     * @param city    foreign key of the city in Cities table.
     * @param username    foreign key of the user in the Users table.
     */
    public StudentUpdateDTO(
            Long id,
            String firstname,
            String lastname,
            String gender,
            String birthdate,
            String city,
            String username
    ) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.city = city;
        this.username = username;
    }

    /**
     * Getter for the id of the student.
     *
     * @return the id of the student.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id of the student
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the firstname of the student.
     *
     * @return the firstname of the student.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for the lastname of the student.
     *
     * @param firstname the firstname.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for the lastname of the student.
     *
     * @return the lastname of the student.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for the lastname of the student.
     *
     * @param lastname the lastname of the student.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentUpdateDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", city='" + city + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
