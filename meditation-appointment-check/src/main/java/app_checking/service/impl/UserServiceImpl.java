package app_checking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app_checking.domain.Role;
import app_checking.domain.User;
import app_checking.dto.request.UserRequest;
import app_checking.dto.response.UserResponse;
import app_checking.repository.UserRepository;
import app_checking.service.UserService;
import app_checking.util.CustomError;
import app_checking.util.mapper.entitytoresponsedto.UserEntityToUserResponseMapper;
import app_checking.util.mapper.requestdtotoentity.UserRequestToUserEntityMapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;


	@Autowired
	UserRequestToUserEntityMapper requestMapper;

	@Autowired
	protected UserEntityToUserResponseMapper responseMapper;

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		List<User> users = userRepository.findByEmail(username);
//
//		if(users == null || users.isEmpty())
//		{
//			throw new UsernameNotFoundException("User not found with email: " + username);
//		}
//		else
//		{
//			List<GrantedAuthority> authorities = new ArrayList<>();
//			User user = users.get(0);
//			//authorities.add(new SimpleGrantedAuthority("ADMIN"));
//			if(user.getRole() != null && !user.getRole().isEmpty())
//			{
//				for(UserRole role : user.getRole())
//				{
//					authorities.add(new SimpleGrantedAuthority(role.getUserRoles().name()));
//				}
//			}
//			return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
//		}
//	}
//
//	@Transactional(readOnly = true)
//	public User getCurrentLoggedInUser()
//	{
//		String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
//		return userRepository.findByEmail(email).get(0);
//	}
//
	
	public UserResponse save(UserRequest userRequest) throws CustomError {
		List<User> tempUser = userRepository.findByEmail(userRequest.getEmail());
		if(tempUser != null && tempUser.size() > 0)
		{
			throw new CustomError(400,"Email already used by another user",null);
		}
		
		User user = requestMapper.map(userRequest);

		//user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setPassword(user.getPassword());
		UserResponse userResponse = responseMapper.map(user);
		User newUser = userRepository.save(user);
		return userResponse;
	}

	@Transactional(readOnly = true)
	public List<UserResponse> findAll() {
		List<User> users = userRepository.findWithRoles();
		return users.stream().map(responseMapper::map).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public UserResponse findById(int userid) {
		Optional<User> user = userRepository.findById(userid);
		return user.map(responseMapper::map).get();
	}


	@Override
	public List<UserResponse> convertEntityListToResponse(List<User> userList) {
		if(null == userList){
			return null;
		}
		else {
			return userList.stream()
					.map(responseMapper::map)
					.collect(Collectors.toList());
		}
	}

	public UserResponse update(int userId, UserRequest userRequest) throws CustomError {
		Optional<User> user = userRepository.findById(userId);
		User oldUser = user.get();
		if(oldUser == null){
			return null;
		}
		if(userRequest.getFirstName() != null && userRequest.getFirstName().length() > 0)
		{
			oldUser.setFirstName(userRequest.getFirstName());
		}
		if(userRequest.getLastName() != null && userRequest.getLastName().length() > 0)
		{
			oldUser.setLastName(userRequest.getLastName());
		}
		if(userRequest.getEmail() != null && userRequest.getEmail().length() > 0)
		{
			List<User> usrLst = userRepository.findByEmail(userRequest.getEmail());
			if(usrLst.get(0).getId() != userId)
			{
				throw new CustomError(400,"Email already used by another user",null);
			}
			oldUser.setEmail(userRequest.getEmail());
		}
		if((userRequest.getGender()+"").length() > 0)
		{
			oldUser.setGender(userRequest.getGender());
		}
		if(userRequest.getPassword() != null && userRequest.getPassword().length() > 0)
		{
			//oldUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
			oldUser.setPassword(userRequest.getPassword());
		}
		if(userRequest.getRoles() != null && userRequest.getRoles().length > 0)
		{
			List<Role> roles = Arrays.stream(userRequest.getRoles()).map(r -> {
				Role role = new Role();
				role.setUserRoles(r);
				return role;
			}).collect(Collectors.toList());
			oldUser.setRole(roles);
		}
		
		return responseMapper.map(oldUser);
	}

	public void delete(int userId) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()){
			userRepository.deleteById(userId);
		}
	}
}
