package gr.aueb.cf.springschoolapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Hibernate Entity class for STUDENTS table.
 * 1 - 1 relation with USERS table
 * N - 1 relation with CITIES table
 * N - N relation with MEETINGS table
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRSTNAME", length = 50, nullable = true, unique = false)
    private String firstname;

    @Column(name = "LASTNAME", length = 50, nullable = false, unique = false)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "GENDER", columnDefinition = "ENUM('M', 'F')")
    private Gender gender;

    @Column(name = "BIRTH_DAY")
    private Date birthDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CITY_ID", nullable = true)
    private City city;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "STUDENTS_MEETINGS",
            joinColumns = @JoinColumn(name="STUDENT_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "MEETING_ID", referencedColumnName = "ID")
    )
    private List<Meeting> meetings = new ArrayList<>();

    /**
     * Convenient method for adding a new meeting
     * for a student.
     *
     * @param meeting instance of {@link Meeting} entity.
     * @return true if the meeting is added successfully,
     * otherwise false.
     */
    public boolean addMeeting(Meeting meeting) {
        if (meeting == null) {
            return false;
        }

        for (Student student : meeting.getStudents()) {
            if (student == this) {
                return false;
            }
        }
        meetings.add(meeting);
        return true;
    }

    /**
     * Convenient method for deleting a meeting from the
     * student.
     *
     * @param meeting instance of {@link Meeting} entity.
     * @return true if the meeting is deleted successfully,
     * otherwise false.
     */
    public boolean deleteMeeting(Meeting meeting) {
        boolean found = false;
        this.meetings.remove(meeting);
        for (Student student : meeting.getAllStudents()) {
            if (student == this) {
                found = true;
                break;
            }
        }
        if (found) {
            meeting.deleteStudent(this);
        }
        return found;
    }

    /**
     * Getter for the id of the entity.
     *
     * @return a long type id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the entity.
     *
     * @param id the id of the entity.
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
     * Setter for the firstname of the student.
     *
     * @param firstname the firstname of the student.
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
     * Getter for the gender of a student.
     *
     * @return the {@link Gender} enum.
     */
    public Gender getGender() {
        return gender;
    }

    /**
     * Setter for the gender of a student.
     *
     * @param gender a {@link Gender} enum with the gender of a student.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Getter for the birthdate of the student.
     *
     * @return {@link Date} with the birthdate of a student.
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Setter for the birthdate of a student.
     *
     * @param birthDate {@link Date} with the birthdate of a student.
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Getter for the {@link City} entity,
     * corresponding to this student.
     *
     * @return an instance of {@link City} entity.
     */
    public City getCity() {
        return city;
    }

    /**
     * Setter for the {@link City} entity,
     * corresponding to this student.
     *
     * @param city an instance of {@link City} entity.
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Getter for the {@link User} entity,
     * corresponding to this student.
     *
     * @return an instance of {@link User} entity.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for the {@link User} entity,
     * corresponding to this student.
     *
     * @param user an instance of {@link User} entity.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Protected Getter for the list of meetings.
     * Clients should not have explicit access.
     *
     * @return a list of meetings.
     */
    protected List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Protected Setter for the list of meetings.
     * Clients should not have explicit access.
     *
     * @param meetings the list of meeting of this student.
     */
    protected void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    /**
     * Returns an unmodifiable view of the list
     * meetings.
     *
     * @return a list of meetings.
     */
    public List<Meeting> getAllMeetings() {
        return Collections.unmodifiableList(meetings);
    }

    /**
     * The state representation of an instance of this
     * entity in a string.
     *
     * @return a string with the values of each field.
     */
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", birthDate=" + birthDate +
//                ", city=" + city.getCityName() +
//                ", username= " + user.getUsername() +
                '}';
    }
}
