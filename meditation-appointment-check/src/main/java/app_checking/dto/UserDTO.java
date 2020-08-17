package app_checking.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import app_checking.domain.UserRoles;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

//Here using jackson we able to ignore null fields not be a part of Json serialized result.
//implements Serizalizable : is just a way to tell , this object can be serialized at transfered through network , there is
//nothing you need to implement.


@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO implements Serializable {
    private int id;
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotBlank(message = "Firstname is mandatory")
    private String firstName;
    @NotBlank(message = "Lastname is mandatory")
    private String lastName;
    private char gender;
    @NotEmpty(message = "User should have atleast one role")
    private UserRoles[] roles;

    public UserDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
