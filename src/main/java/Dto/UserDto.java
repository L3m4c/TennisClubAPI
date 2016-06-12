package dto;

import entity.User.User;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    private long id;
    @NotNull
    private String email;
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @NotNull
    private String role;

    public UserDto() {}
    public UserDto(User user){
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.role = user.getRole();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
