package gr.aueb.cf.springschoolapp.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Hibernate Entity class for SPECIALITIES table.
 * 1 - N relation with TEACHERS table
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "SPECIALITIES")
public class Speciality {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SPECIALITY", length = 50, nullable = false, unique = true)
    private String specialityName;

    @OneToMany(mappedBy = "speciality", fetch = FetchType.EAGER)
    private List<Teacher> teachers = new ArrayList<>();

    /**
     * Convenient method for adding a new teacher in for
     * a certain speciality.
     *
     * @param teacher {@link Teacher} entity.
     * @return true if the teacher is added successfully,
     * otherwise false.
     */
    public boolean addTeacher(Teacher teacher) {
        if (teacher == null || teacher.getSpeciality() == this) {
            return false;
        }
        teacher.setSpeciality(this);
        this.teachers.add(teacher);
        return true;
    }

    /**
     * Getter for the id of the speciality.
     *
     * @return a long type id.
     */
    public Long getId() {
        return id;
    }

    /**
     * Setter for the id of the speciality.
     *
     * @param id a long type id.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the speciality name.
     *
     * @return the speciality name.
     */
    public String getSpecialityName() {
        return specialityName;
    }

    /**
     * Setter for the speciality name.
     *
     * @param speciality the speciality name of this instance.
     */
    public void setSpecialityName(String speciality) {
        this.specialityName = speciality;
    }

    /**
     * Protected Getter for the list of students.
     * Clients should not have explicit access.
     *
     * @return a list of teachers.
     */
    protected List<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Protected Setter for the list of teachers.
     * Clients should not have explicit access.
     *
     * @param teachers {@link List} object.
     */
    protected void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    /**
     * Returns an unmodifiable view of the list
     * teachers.
     *
     * @return a list of teachers.
     */
//    public List<Teacher> getAllTeachers() {
//        return Collections.unmodifiableList(teachers);
//    }

    /**
     * The state representation of an instance of this
     * entity in a string.
     *
     * @return a string with the values of each field.
     */
    @Override
    public String toString() {
        return "Speciality{" +
                "id=" + id +
                ", speciality='" + specialityName + '\'' +
                '}';
    }
}
