package gr.aueb.cf.springschoolapp.model;


import javax.persistence.*;

/**
 * Hibernate Entity class for USERS table.
 * 1 - 1 relation with STUDENTS table
 * 1 - 1 relation with TEACHERS table.
 *
 * @author Thanasis Chousiadas
 */
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", length = 50, nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD", length = 150, nullable = false, unique = false)
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;

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
     * Getter for the username of the entity.
     *
     * @return the username of the entity.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the entity.
     *
     * @param username the username of the entity.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password of the entity.
     *
     * @return a string with the password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of the entity.
     *
     * @param password a string with the password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Getter for the {@link Teacher} entity which has a 1 - 1 relation.
     * Null if there is no relation.
     *
     * @return an instance of {@link Teacher}.
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * Setter for the {@link Teacher} entity which has a 1 - 1 relation.
     *
     * @param teacher a {@link Teacher} object or null if there is no relation.
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    /**
     * Getter for the {@link Student} entity which has a 1 - 1 relation.
     * Null if there is no relation.
     *
     * @return an instance of {@link Student}.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Setter for the {@link Student} entity which has a 1 - 1 relation.
     *
     * @param student a {@link Student} object or null if there is no relation.
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * The state representation of an instance of this
     * entity in a string.
     *
     * @return a string with the values of each field.
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
