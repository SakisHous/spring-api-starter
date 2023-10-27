package gr.aueb.cf.springschoolapp.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hibernate Entity class for TEACHERS table.
 * 1 - 1 relation with USERS table
 * N - 1 relation with SPECIALITIES table
 * 1 - N relation with MEETINGS table.
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "TEACHERS")
public class Teacher {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SSN", length = 6, nullable = false, unique = true)
    private String ssn;

    @Column(name = "FIRSTNAME", length = 50, nullable = true, unique = false)
    private String firstname;

    @Column(name = "LASTNAME", length = 50, nullable = false, unique = false)
    private String lastname;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPECIALITY_ID", nullable = true)
    private Speciality speciality;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private User user;

    @OneToMany(mappedBy = "teacher")
    private List<Meeting> meetings = new ArrayList<>();

    /**
     * Convenient method for adding a new meeting
     * for a teacher.
     *
     * @param meeting the meeting to be added.
     * @return true is it is added successfully,
     * otherwise false.
     */
    public boolean addMeeting(Meeting meeting) {
        if (meeting == null || meeting.getTeacher() == this) {
            return false;
        }
        meeting.setTeacher(this);
        meetings.add(meeting);
        return true;
    }

    /**
     * Getter for id field of the entity.
     *
     * @return a long type id
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the entity.
     *
     * @param id a long type id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the Social Security Number of the entity
     * {@link Teacher}.
     *
     * @return a string with the ssn.
     */
    public String getSsn() {
        return ssn;
    }

    /**
     * Setter for the Social Security Number of the entity
     * {@link Teacher}.
     *
     * @param ssn a string with the ssn.
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

    /**
     * Getter fot the {@link Speciality} entity,
     * corresponding to this teacher.
     *
     * @return {@link Speciality} object.
     */
    public Speciality getSpeciality() {
        return speciality;
    }

    /**
     * Setter for the {@link Speciality} entity,
     * corresponding to this teacher.
     *
     * @param speciality {@link Speciality} object.
     */
    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    /**
     * Getter for the {@link User} entity,
     * corresponding to this teacher.
     *
     * @return {@link User} object.
     */
    public User getUser() {
        return user;
    }

    /**
     * Setter for the {@link User} entity,
     * corresponding to this teacher.
     *
     * @param user {@link User} object.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Protected Getter for the list of meetings
     * of this teacher. Clients should not have
     * explicit access.
     *
     * @return a list of meetings.
     */
    protected List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Protected Setter for the list of meetings
     * of this teacher. Clients should not have
     * explicit access.
     *
     * @param meetings {@link List} object.
     */
    protected void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    /**
     * Returns an unmodifiable view of the list
     * meetings.
     *
     * @return a list of meetingss.
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
        return "Teacher{" +
                "id=" + id +
                ", ssn='" + ssn + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
