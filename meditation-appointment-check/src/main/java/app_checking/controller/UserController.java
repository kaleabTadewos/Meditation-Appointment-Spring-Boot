package app_checking.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app_checking.dto.request.UserRequest;
import app_checking.dto.response.UserResponse;
import app_checking.service.UserService;
import app_checking.util.CustomError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Api(value = "Users of TM Checking", description = "Operations pertaining to user in Users of TM Checking")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping()
	@ApiOperation(value = "Create new user", response = UserResponse.class)
	public UserResponse createUser(@Valid @RequestBody UserRequest user) throws CustomError {
		return userService.save(user);
	}

//	@PostMapping("/login")
//	@ApiOperation(value = "User login")
//	public ResponseEntity<?> login(@Valid @RequestBody LogInRequest req) {
//		try {
//			authenticate(req.getUsername(), req.getPassword());
//		} catch (Exception exc) {
//			exc.printStackTrace();
//			return ResponseEntity.status(401).body("Invalid credentials");
//		}
//		final UserDetails userDetails = userService.loadUserByUsername(req.getUsername());
//
//		final String token = jwtTokenUtil.generateToken(userDetails);
//
//		return ResponseEntity.ok(new LogInResponse(token));
//	}

//	private void authenticate(String email, String password) throws Exception {
//		try {
//			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
//		} catch (DisabledException e) {
//			throw new Exception("USER_DISABLED", e);
//		} catch (BadCredentialsException e) {
//			throw new Exception("INVALID_CREDENTIALS", e);
//		}
//	}

	@GetMapping()
	@ApiOperation(value = "View a list of available users", response = UserResponse.class)
	public List<UserResponse> getUsers() {
		return userService.findAll();
	}

	@GetMapping("/{userid}")
	@ApiOperation(value = "View user using ID", response = UserResponse.class)
	public UserResponse getUserById(@PathVariable int userid) {
		return userService.findById(userid);
	}

	@PutMapping("/{userid}")
	@ApiOperation(value = "Update user using ID", response = UserResponse.class)
	public UserResponse updateById(@PathVariable int userid, @RequestBody UserRequest user) throws CustomError {
		return userService.update(userid, user);
	}

	@DeleteMapping("/{userid}")
	@ApiOperation(value = "Delete user using ID", response = void.class)
	public void deleteUser(@PathVariable int userid) {
		userService.delete(userid);
	}

}