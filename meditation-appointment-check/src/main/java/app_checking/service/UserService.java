package app_checking.service;

import app_checking.domain.User;
import app_checking.dto.request.UserRequest;
import app_checking.dto.response.UserResponse;
import app_checking.util.CustomError;

import java.util.List;

//import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService //extends UserDetailsService 
{

	UserResponse save(UserRequest user) throws CustomError;
    List<UserResponse> findAll();
    UserResponse update(int userId,UserRequest user) throws CustomError;
    void delete(int userId);
    UserResponse findById(int userId);
}
