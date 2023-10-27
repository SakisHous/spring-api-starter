package gr.aueb.cf.springschoolapp.dto.teacherdto;

public class TeacherReadOnlyDTO {
    private Long id;
    private String ssn;
    private String firstname;
    private String lastname;
    private String specialityName;
    private String username;

    public TeacherReadOnlyDTO() {
    }

    public TeacherReadOnlyDTO(Long id, String ssn, String firstname, String lastname, String specialityName, String username) {
        this.id = id;
        this.ssn = ssn;
        this.firstname = firstname;
        this.lastname = lastname;
        this.specialityName = specialityName;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
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

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TeacherReadOnlyDTO{" +
                "id=" + id +
                ", ssn='" + ssn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", specialityName='" + specialityName + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
