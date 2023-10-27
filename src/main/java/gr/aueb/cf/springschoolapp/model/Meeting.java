package gr.aueb.cf.springschoolapp.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Hibernate Entity class for MEETINGS table.
 * N - 1 relation with TEACHERS table
 * N - N relation with STUDENTS table
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "MEETINGS")
public class Meeting {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "MEETING_ROOM", length = 45, nullable = true, unique = false)
    private String meetingRoom;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "MEETING_DATETIME")
    private Date meetingDatetime;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEACHER_ID", nullable = true)
    private Teacher teacher;
    @ManyToMany
    @JoinTable(
            name = "MEETINGS_STUDENTS",
            joinColumns = @JoinColumn(name = "MEETING_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID")
    )
    private List<Student> students = new ArrayList<>();

    /**
     * Convenient method for adding a new student
     * for a meeting.
     *
     * @param student instance of {@link Student} entity.
     * @return true if the student is added successfully,
     * otherwise false.
     */
    public boolean addStudent(Student student) {
        if (student == null) {
            return false;
        }
        for (Meeting meeting : student.getMeetings()) {
            if (meeting == this) {
                return false;
            }
        }
        students.add(student);
        return true;
    }

    /**
     * Convenient method for deleting a student from the
     * meeting.
     *
     * @param student instance of {@link Student} entity.
     * @return true if the student is deleted successfully,
     * otherwise false.
     */
    public boolean deleteStudent(Student student) {
        boolean found = false;
        this.students.remove(student);
        for (Meeting meeting : student.getAllMeetings()) {
            if (meeting == this) {
                found = true;
                break;
            }
        }
        if (found) {
            student.deleteMeeting(this);
        }
        return found;
    }

    /**
     * Getter for the id of the meeting.
     *
     * @return a long type id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the meeting.
     *
     * @param id the id in a long type.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the meeting room of this meeting
     * instance.
     *
     * @return a string with the name of meeting room.
     */
    public String getMeetingRoom() {
        return meetingRoom;
    }

    /**
     * Setter for the meeting room of this meeting
     * instance.
     *
     * @param meetingRoom a string with the name of the meeting room.
     */
    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    /**
     * Getter for the timestamp of the meeting.
     *
     * @return a {@link Date} object which represents the timestamp.
     */
    public Date getMeetingDatetime() {
        return meetingDatetime;
    }

    /**
     * Setter for the timestamp of the meeting.
     *
     * @param meetingDatetime a {@link Date} object which has
     *                        the information of a timestamp
     */
    public void setMeetingDatetime(Date meetingDatetime) {
        this.meetingDatetime = meetingDatetime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Protected Getter for the list of students.
     * Clients should not have explicit access.
     *
     * @return a list of students.
     */
    protected List<Student> getStudents() {
        return students;
    }

    /**
     * Protected Setter for the list of students.
     * Clients should not have explicit access.
     *
     * @param students the list of meeting of this student.
     */
    protected void setStudents(List<Student> students) {
        this.students = students;
    }

    /**
     * Returns an unmodifiable view of the list
     * students.
     *
     * @return a list of students.
     */
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", meetingRoom='" + meetingRoom + '\'' +
                ", meetingDatetime=" + meetingDatetime +
                '}';
    }
}
