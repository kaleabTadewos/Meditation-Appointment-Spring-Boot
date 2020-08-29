package app_checking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import app_checking.domain.Role;
import app_checking.domain.User;
import app_checking.domain.UserRoles;
import app_checking.dto.request.UserRequest;
import app_checking.dto.response.UserResponse;
import app_checking.repository.UserRepository;
import app_checking.service.impl.UserServiceImpl;
import app_checking.util.CustomError;
import app_checking.util.mapper.entitytoresponsedto.UserEntityToUserResponseMapper;
import app_checking.util.mapper.requestdtotoentity.UserRequestToUserEntityMapper;
import ma.glasnost.orika.MapperFactory;

@SpringBootTest
public class UserServiceTest {
	
//	@Spy
//	private MapperFactory mapperFactory;
	
	@MockBean
	private UserRepository userRepository;
	
	@MockBean
	UserRequestToUserEntityMapper requestMapper;
	
	@MockBean
	UserEntityToUserResponseMapper responseMapper;
	
	@InjectMocks
	private UserServiceImpl userService;
	
	private static User userMock1;
	private static User updateUserMock1;
	private static UserRequest mockUserRequest;
	private static UserResponse mockUserResponse;
	private static List<User> userList;
	private static List<UserResponse> userResponseList;
	private static UserRequest updateUserRequest;
	private static Optional<User> optionalUserMock1;
	private static List<User> userMock1List;
	
	@BeforeEach
    public void setup() {
		MockitoAnnotations.initMocks(this);
		when(userRepository.save(userMock1)).thenReturn(userMock1);
		when(requestMapper.map(mockUserRequest)).thenReturn(userMock1);
		when(responseMapper.map(userMock1)).thenReturn(mockUserResponse);
		when(userRepository.findWithRoles()).thenReturn(userList);
		when(responseMapper.mapList(userList)).thenReturn(userResponseList);
		when(userRepository.findById(1)).thenReturn(optionalUserMock1);
		when(userRepository.findByEmail("kaleabero@gmail.com")).thenReturn(userMock1List);
		when(userRepository.findByEmail("tadewoskaleab@gmail.com")).thenReturn(null);
	}
	
	@BeforeAll
	public static void prepareMocks() {
		Role role = new Role();
		role.setUserRoles(UserRoles.ADMIN);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		Role role22 = new Role();
		role22.setUserRoles(UserRoles.STUDENT);
		List<Role> roless = new ArrayList<>();
		roless.add(role22);
		
		UserRoles[] role2 = {UserRoles.STUDENT};
		
		userMock1 = new User();
		userMock1.setFirstName("kaleab");
		userMock1.setLastName("tadewos");
		userMock1.setEmail("kaleabero@gmail.com");
		userMock1.setGender('M');
		userMock1.setPassword("123");
		userMock1.setRole(roles);
		
		updateUserMock1 = new User();
		updateUserMock1.setFirstName("kal");
		updateUserMock1.setLastName("tad");
		updateUserMock1.setEmail("tadewoskaleab@gmail.com");
		updateUserMock1.setGender('F');
		updateUserMock1.setPassword("234");
		updateUserMock1.setRole(roless);
		
		optionalUserMock1 = Optional.of(userMock1);
		
		userMock1List = new ArrayList<>();
		userMock1List.add(userMock1);
		
		updateUserRequest = new UserRequest();
		updateUserRequest.setFirstName("kal");
		updateUserRequest.setLastName("tad");
		updateUserRequest.setEmail("tadewoskaleab@gmail.com");
		updateUserRequest.setGender('F');
		updateUserRequest.setPassword("234");
		updateUserRequest.setRoles(role2);
		
		
		//List of users
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		//List of UserResponses
		UserResponse userResponse1 = new UserResponse();
		UserResponse userResponse2 = new UserResponse();
		UserResponse userResponse3 = new UserResponse();
		
		mockUserRequest = new UserRequest();
		mockUserRequest.setFirstName("kaleab");
		mockUserRequest.setLastName("tadewos");
		
		mockUserResponse = new UserResponse();
		mockUserResponse.setFirstName("kaleab");
		mockUserResponse.setLastName("tadewos");
		
		userResponse1.setFirstName("user1");
		userResponse2.setFirstName("user2");
		userResponse3.setFirstName("user3");
		
		user1.setFirstName("user1");
		user2.setFirstName("user2");
		user3.setFirstName("user3");
		
		userList = new ArrayList<User>();
		userList.add(user1);
		userList.add(user2);
		userList.add(user3);
		
		userResponseList = new ArrayList<>();
		userResponseList.add(userResponse1);
		userResponseList.add(userResponse2);
		userResponseList.add(userResponse3);
	}
	
	@Test
	public void testSaveUser() throws CustomError {
		// Create a contact
		
//		User userMock = new User();
//		userMock.setFirstName("kaleab");
//		userMock.setLastName("tadewos");
//		
//		UserRequest mockUserRequest = new UserRequest();
//		mockUserRequest.setFirstName("kaleab");
//		mockUserRequest.setLastName("tadewos");
//		
//		UserResponse mockUserResponse = new UserResponse();
//		mockUserResponse.setFirstName("kaleab");
//		mockUserResponse.setLastName("tadewos");
		
		
//		when(userRepository.save(userMock1)).thenReturn(userMock1);
//		when(requestMapper.map(mockUserRequest)).thenReturn(userMock1);
//		when(responseMapper.map(userMock1)).thenReturn(mockUserResponse);
		// Save the contact
		
		UserResponse userResponse = userService.save(mockUserRequest);
		
		// Verify the save
		assertEquals("kaleab", userResponse.getFirstName());
		assertEquals("tadewos", userResponse.getLastName());
	}
	
	@Test
	public void testfindAllUser() {

//		User user1 = new User();
//		User user2 = new User();
//		User user3 = new User();
//		
//		UserResponse userResponse1 = new UserResponse();
//		UserResponse userResponse2 = new UserResponse();
//		UserResponse userResponse3 = new UserResponse();
//		
//		userResponse1.setFirstName("user1");
//		userResponse2.setFirstName("user2");
//		userResponse3.setFirstName("user3");
//		
//		user1.setFirstName("user1");
//		user2.setFirstName("user2");
//		user3.setFirstName("user3");
//		
//		List<User> userList = new ArrayList<User>();
//		userList.add(user1);
//		userList.add(user2);
//		userList.add(user3);
//		
//		List<UserResponse> userResponseList = new ArrayList<>();
//		userResponseList.add(userResponse1);
//		userResponseList.add(userResponse2);
//		userResponseList.add(userResponse3);

//		when(userRepository.findWithRoles()).thenReturn(userList);
//		when(responseMapper.mapList(userList)).thenReturn(userResponseList);
		
		List<UserResponse> result = userService.findAll();
		
		assertEquals(3, result.size());
	}
//	
//	@Test
//	public void testfindByIdUser() {
//		// Create a contact
//		
//		User userMock = new User();
//		userMock.setFirstName("kaleab");
//		userMock.setLastName("tadewos");
//		Optional<User> optionalUser = Optional.of(userMock);
//		
//		when(userRepository.findById(1)).thenReturn(optionalUser);
//		
//		UserRequest userRequest = new UserRequest();
//		userRequest.setFirstName("kaleab");
//		userRequest.setLastName("tadewos");
//		
//		UserResponse userResponse = userService.findById(1);
//		
//		// Verify the save
//		assertEquals("kaleab", userResponse.getFirstName());
//		assertEquals("tadewos", userResponse.getLastName());
//	}
//	
	@Test
	public void testUpdateUser() throws CustomError {
			
		Role role = new Role();
		role.setUserRoles(UserRoles.ADMIN);
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		UserRoles[] role2 = {UserRoles.STUDENT};
//		
		User userMock = new User();
		userMock.setFirstName("kaleab");
		userMock.setLastName("tadewos");
		userMock.setEmail("kaleabero@gmail.com");
		userMock.setGender('M');
		userMock.setPassword("123");
		userMock.setRole(roles);
		
		List<User> userMock2 = new ArrayList<>();
		userMock2.add(userMock);
		
		when(userRepository.findById(1)).thenReturn(optionalUserMock1);
		when(userRepository.findByEmail("kaleabero@gmail.com")).thenReturn(userMock1List);
		
		// Save the contact
		UserRequest userRequest = new UserRequest();
		userRequest.setFirstName("kal");
		userRequest.setLastName("tad");
		userRequest.setEmail("tadewoskaleab@gmail.com");
		userRequest.setGender('F');
		userRequest.setPassword("234");
		userRequest.setRoles(role2);
		
		//mockUserResponse.setFirstName("kal");
		
		when(responseMapper.map(Mockito.any( User.class ))).thenAnswer( new Answer<UserResponse>() {
			public UserResponse answer(InvocationOnMock invocation) {
			     Object[] args = invocation.getArguments();
			     //Object mock = invocation.getMock();
			     return maptoUserResponse((User)args[0]);
			     }
		});
		
		UserResponse userResponse = userService.update(1, updateUserRequest);
		
		// Verify the save
		assertEquals("kal", userResponse.getFirstName());
		assertEquals("tad", userResponse.getLastName());
		assertEquals("tadewoskaleab@gmail.com", userResponse.getEmail());
		assertEquals('F', userResponse.getGender());
		//password and role is not found on userResponse ,it needs another check.
	}
//	
	@Test
	public void testDeleteUser() {
//		User userMock = new User();
//		Optional<User> optionalUser = Optional.of(userMock);
		when(userRepository.findById(1)).thenReturn(optionalUserMock1);		
	    doNothing().when(userRepository).deleteById(1);
		
		userService.delete(1);
		//this means , verify if userRepository findbyid and deletebyid is called with argument 1.
		verify(userRepository).findById(1);
		verify(userRepository).deleteById(1);
		
	}
	
	public static UserResponse maptoUserResponse(User user) {
		UserResponse userResponse = new UserResponse();
		
		userResponse.setFirstName(user.getFirstName());
		userResponse.setLastName(user.getLastName());
		userResponse.setEmail(user.getEmail());
		userResponse.setGender(user.getGender());
		
		return userResponse;
	}
	
}
