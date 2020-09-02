package app_checking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import app_checking.domain.User;
import app_checking.dto.request.UserRequest;
import app_checking.repository.UserRepository;
import app_checking.service.UserService;

@Component
public class Init implements CommandLineRunner {

//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
//	
//	@Autowired
//	private UserService userService;
	
	@Override
	public void run(String... args) throws Exception {
//		User user = userRepository.findById(1).get();
//		UserRequest userRequest = new UserRequest();
//		userRequest.setPassword(passwordEncoder.encode("234"));
//		userService.update(1, userRequest);
//		
//		
//		System.out.println(user.getEmail());
//		System.out.println("Username for the user : " + userRepository.findByUserName("kal").get(0).getUsername());
	}

}
