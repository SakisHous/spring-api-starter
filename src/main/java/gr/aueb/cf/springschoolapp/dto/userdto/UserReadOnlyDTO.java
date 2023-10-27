package gr.aueb.cf.springschoolapp.dto.userdto;

public class UserReadOnlyDTO {
    private Long id;
    private String username;

    public UserReadOnlyDTO() {
    }

    public UserReadOnlyDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
