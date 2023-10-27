package gr.aueb.cf.springschoolapp.dto.userdto;

/**
 * The Data Transfer Object for data needed in update
 * operation of a {@link gr.aueb.cf.springschoolapp.model.User}
 *
 * @author Thanasis Chousiadas
 */
public class UserUpdateDTO {
    private Long id;
    private String username;
    private String password;

    /**
     * Default constructor.
     */
    public UserUpdateDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id       the id (primary key) is needed for update the record.
     * @param username the username to be updated.
     * @param password the password to be updated.
     */
    public UserUpdateDTO(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * @return id of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id of the teacher
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Getter for the username.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username.
     *
     * @param username the username of the User.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for the password of the User.
     *
     * @return the password of the User.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for the password of the user.
     *
     * @param password the password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
