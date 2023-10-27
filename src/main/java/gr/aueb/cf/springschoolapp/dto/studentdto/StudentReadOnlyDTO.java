package gr.aueb.cf.springschoolapp.dto.studentdto;


public class StudentReadOnlyDTO {
    private Long id;
    private String firstname;
    private String lastname;
    private String gender;
    private String birthdate;
    private String city;
    private String username;

    public StudentReadOnlyDTO() {
    }

    public StudentReadOnlyDTO(Long id, String firstname, String lastname, String gender, String birthdate, String city, String username) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.gender = gender;
        this.birthdate = birthdate;
        this.city = city;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

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
        return "StudentReadOnlyDTO{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthdate=" + birthdate +
                ", username='" + username + '\'' +
                '}';
    }
}
