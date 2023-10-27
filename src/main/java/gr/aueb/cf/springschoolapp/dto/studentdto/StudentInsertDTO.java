package gr.aueb.cf.springschoolapp.dto.studentdto;


/**
 * The Data Transfer Object for data needed in
 * insert operation of a {@link gr.aueb.cf.springschoolapp.model.Student}
 *
 * @author Thanasis Chousiadas
 */
public class StudentInsertDTO {
    private String firstname;
    private String lastname;
    private String gender;
    private String birthdate;
    private String city;
    private String username;

    /**
     * Default constructor.
     */
    public StudentInsertDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param firstname the firstname of the student.
     * @param lastname  the lastname of the student.
     * @param gender    the gender of the student.
     * @param birthdate the birthdate of the student.
     * @param city    foreign key of the city in Cities table.
     * @param username    foreign key of the user in the Users table.
     */
    public StudentInsertDTO(
            String firstname, String lastname, String gender, String birthdate, String city, String username) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.city = city;
        this.username = username;
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

    /**
     * Getter for the gender of the student.
     *
     * @return the gender of the student.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Setter for the gender of the student.
     *
     * @param gender the gender of the student.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Getter for the birthdate of the student.
     *
     * @return the birthdate of the student.
     */
    public String getBirthdate() {
        return birthdate;
    }

    /**
     * Setter for the birthdate of the student.
     *
     * @param birthdate the brithdate of the student.
     */
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
        return "StudentInsertDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", city='" + city + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
