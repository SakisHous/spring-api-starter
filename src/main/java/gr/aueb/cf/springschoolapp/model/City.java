package gr.aueb.cf.springschoolapp.model;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hibernate Entity class for CITIES table.
 * 1 - N relation with STUDENTS table
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "CITIES")
public class City {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CITY", length = 50, nullable = false, unique = true)
    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.EAGER)
    private List<Student> students = new ArrayList<>();


    /**
     * Convenient method for adding a student
     * for a certain city.
     *
     * @param student instance of entity {@link Student}
     * @return true if the student is added successfully,
     * otherwise false.
     */
    public boolean addStudent(Student student) {
        if (student == null || student.getCity() == this) {
            return false;
        }
        student.setCity(this);
        this.students.add(student);
        return true;
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
     * @param id a long type id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the city name of this instance of
     * the entity.
     *
     * @return a string with the name of the city.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Setter for the city name of this instance of
     * the entity.
     *
     * @param city a string with the city name.
     */
    public void setCityName(String city) {
        this.cityName = city;
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
     * @param students the list of student of this city.
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

    /**
     * The state representation of an instance of this
     * entity in a string.
     *
     * @return a string with the values of each field.
     */
    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", city='" + cityName + '\'' +
                '}';
    }
}
