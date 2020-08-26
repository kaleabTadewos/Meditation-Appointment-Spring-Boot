package app_checking.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import app_checking.domain.UserRoles;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse implements Serializable {
    private int id;
    private String email;
    private String firstName;
    private String lastName;
    private char gender;
    private UserRoles[] roles;

    public UserResponse() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public UserRoles[] getRoles() {
        return roles;
    }

    public void setRoles(UserRoles[] roles) {
        this.roles = roles;
    }
}
