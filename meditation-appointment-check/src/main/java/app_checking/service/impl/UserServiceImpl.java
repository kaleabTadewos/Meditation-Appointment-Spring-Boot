//package app_checking.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.authority.SimpleGrantedAuthority;
////import org.springframework.security.core.context.SecurityContextHolder;
////import org.springframework.security.core.userdetails.UserDetails;
////import org.springframework.security.core.userdetails.UsernameNotFoundException;
////import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import app_checking.domain.User;
//import app_checking.domain.UserRole;
//import app_checking.dto.UserDTO;
//import app_checking.repository.UserRepository;
//import app_checking.service.UserService;
//import app_checking.util.CustomError;
//import app_checking.util.mapper.CustomObjectMapper;
//import app_checking.util.mapper.UserResponseMapper;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//
//@Transactional(propagation = Propagation.REQUIRES_NEW)
//public class UserServiceImpl implements UserService {
//	
//	@Autowired
//	UserRepository userRepository;
//
////	@Autowired
////	PasswordEncoder passwordEncoder;
//
//	@Autowired
//	CustomObjectMapper objectMapper;
//
//	@Autowired
//	protected UserResponseMapper responseMapper;
//
////	@Override
////	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////		List<User> users = userRepository.findByEmail(username);
////
////		if(users == null || users.isEmpty())
////		{
////			throw new UsernameNotFoundException("User not found with email: " + username);
////		}
////		else
////		{
////			List<GrantedAuthority> authorities = new ArrayList<>();
////			User user = users.get(0);
////			//authorities.add(new SimpleGrantedAuthority("ADMIN"));
////			if(user.getRole() != null && !user.getRole().isEmpty())
////			{
////				for(UserRole role : user.getRole())
////				{
////					authorities.add(new SimpleGrantedAuthority(role.getUserRoles().name()));
////				}
////			}
////			return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);
////		}
////	}
////
////	@Transactional(readOnly = true)
////	public User getCurrentLoggedInUser()
////	{
////		String email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
////		return userRepository.findByEmail(email).get(0);
////	}
////
//	public UserDTO save(UserDTO userDTO) throws CustomError {
//		List<User> tempUser = userRepository.findByEmail(userDTO.getEmail());
//		if(tempUser != null && tempUser.size() > 0)
//		{
//			throw new CustomError(400,"Email already used by another user",null);
//		}
//		User user = objectMapper.getUserEntityFromDTO(userDTO);
//
//		//user.setPassword(passwordEncoder.encode(user.getPassword()));
//		user.setPassword(user.getPassword());
//		User newUser = userRepository.save(user);
//		userDTO.setPassword(null);
//		userDTO.setId(newUser.getId());
//		return userDTO;
//	}
//
//	@Transactional(readOnly = true)
//	public List<UserDTO> findAll() {
//		List<User> users = userRepository.findWithRoles();
//		return users.stream().map(objectMapper::getUserDTOFromEntity).collect(Collectors.toList());
//	}
//
//	@Transactional(readOnly = true)
//	public UserDTO findById(int userid) {
//		Optional<User> user = userRepository.findById(userid);
//		return user.map(objectMapper::getUserDTOFromEntity).get();
//	}
//
//
//	@Override
//	public List<UserDTO> convertEntityListToResponse(List<User> userList) {
//		if(null == userList){
//			return null;
//		}
//		else {
//			return userList.stream()
//					.map(responseMapper::map)
//					.collect(Collectors.toList());
//		}
//	}
//
//	public UserDTO update(int userId, UserDTO userDTO) throws CustomError {
//		Optional<User> user = userRepository.findById(userId);
//		User oldUser = user.get();
//		if(oldUser == null){
//			return null;
//		}
//		if(userDTO.getFirstName() != null && userDTO.getFirstName().length() > 0)
//		{
//			oldUser.setFirstName(userDTO.getFirstName());
//		}
//		if(userDTO.getLastName() != null && userDTO.getLastName().length() > 0)
//		{
//			oldUser.setLastName(userDTO.getLastName());
//		}
//		if(userDTO.getEmail() != null && userDTO.getEmail().length() > 0)
//		{
//			List<User> usrLst = userRepository.findByEmail(userDTO.getEmail());
//			if(usrLst.get(0).getId() != userId)
//			{
//				throw new CustomError(400,"Email already used by another user",null);
//			}
//			oldUser.setEmail(userDTO.getEmail());
//		}
//		if((userDTO.getGender()+"").length() > 0)
//		{
//			oldUser.setGender(userDTO.getGender());
//		}
//		if(userDTO.getPassword() != null && userDTO.getPassword().length() > 0)
//		{
//			//oldUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
//			oldUser.setPassword(userDTO.getPassword());
//		}
//		if(userDTO.getRoles() != null && userDTO.getRoles().length > 0)
//		{
//			List<UserRole> roles = Arrays.stream(userDTO.getRoles()).map(role -> {
//				UserRole rle = new UserRole();
//				rle.setUserRoles(role);
//				return rle;
//			}).collect(Collectors.toList());
//			oldUser.setRole(roles);
//		}
//		UserDTO rslt = objectMapper.getUserDTOFromEntity(userRepository.save(oldUser));
//		rslt.setPassword(null);
//		return rslt;
//	}
//
//	public void delete(int userId) {
//		Optional<User> user = userRepository.findById(userId);
//		if(user.isPresent()){
//			userRepository.deleteById(userId);
//		}
//	}
//}
