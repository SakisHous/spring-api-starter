package gr.aueb.cf.springschoolapp.dto.teacherdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * The Data Transfer Object for data needed in insert
 * operation of a {@link gr.aueb.cf.schoolapp.model.Teacher}
 *
 * @author Thanasis Chousiadas
 */
public class TeacherInsertDTO {
    @NotBlank(message = "SSN is mandatory")
    @Size(min = 6, max = 6, message = "SSN must contain 9 characters")
    private String ssn;
    @NotBlank(message = "Firstname is mandatory")
    @Size(min = 3, max = 52, message = "Firstname must be between 9 - 52 characters")
    private String firstname;
    @NotBlank(message = "Lastname is mandatory")
    @Size(min = 3, max = 52, message = "Lastname must be between 9 - 52 characters")
    private String lastname;
    private String speciality;
    @NotBlank(message = "Username is mandatory")
    private String username;

    /**
     * Default constructor.
     */
    public TeacherInsertDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param ssn        the Social Security Number of the teacher.
     * @param firstname  the firstname of the teacher.
     * @param lastname   the lastname of the teacher.
     * @param speciality the speciality column in Specialities table.
     * @param username   the username column in Users table.
     */
    public TeacherInsertDTO(
            String ssn,
            String firstname,
            String lastname,
            String speciality,
            String username
    ) {
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.speciality = speciality;
        this.username = username;
    }

    /**
     * Getter for SSN of the teacher.
     *
     * @return the SSN of the teacher.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Setter for the SSN of the teacher.
     *
     * @param ssn the SSN of the teacher.
     */
    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    /**
     * Getter for the firstname of the teacher.
     *
     * @return the firstname of the teacher.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Setter for the firstname of the teacher.
     *
     * @param firstname the firstname of the teacher.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Getter for the lastname of the teacher.
     *
     * @return the lastname of the teacher.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Setter for the lastname of the teacher.
     *
     * @param lastname the lastname of the teacher.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TeacherInsertDTO{" +
                "ssn='" + ssn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", speciality='" + speciality + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
