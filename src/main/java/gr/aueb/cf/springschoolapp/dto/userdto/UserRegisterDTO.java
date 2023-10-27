package gr.aueb.cf.springschoolapp.dto.userdto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * The Data Transfer Object for data needed in insert
 * operation of a {@link gr.aueb.cf.springschoolapp.model.User}
 *
 * @author Thanasis Chousiadas
 */
public class UserRegisterDTO {
    private Long id;
    @NotBlank
    @Size(min = 3, max = 52, message = "")
    private String username;
    @NotBlank
    @Pattern(regexp = "(?=[a-zA-Z0-9#?!@$%^&*-]*?[A-Z])(?=[a-zA-Z0-9#?!@$%^&*-]*?[a-z])(?=[a-zA-Z0-9#?!@$%^&*-]*?[0-9])(?=[a-zA-Z0-9#?!@$%^&*-]*?[#?!@$%^&*-])^.{8,}")
    private String password;
    @NotBlank
    private String confirmPassword;

    /**
     * Default constructor.
     */
    public UserRegisterDTO() {
    }

    /**
     * Overloaded constructor.
     *
     * @param id       it is ignored, primary key is provided by the database in insert operation.
     * @param username the username of the new User to inserted.
     * @param password the password of the new User to be inserted.
     * @param confirmPassword the confirmed password of the new User to be inserted.
     */
    public UserRegisterDTO(Long id, String username, String password, String confirmPassword) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    /**
     * @return id of the user
     */
    public Long getId() {
        return id;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegisterDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
