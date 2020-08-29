package app_checking;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import app_checking.controller.UserController;
import app_checking.domain.User;
import app_checking.domain.UserRoles;
import app_checking.dto.request.UserRequest;
import app_checking.dto.response.UserResponse;
import app_checking.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private UserController contactsManagementController;
	
	private List<UserResponse> userResponses;
	private UserRequest userRequest;
	private UserResponse userResponse;
	private User user;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);	
		
		UserRoles[] roles = {UserRoles.STUDENT};
		userResponses = new ArrayList<>();
		
	
		
		userResponse = new UserResponse();
		userResponse.setFirstName("kaleab");
		userResponse.setLastName("tadewos");
		userResponse.setGender('m');
		userResponse.setEmail("tadewoskaleab@gmail.com");
		userResponse.setRoles(roles);
		
		userRequest = new UserRequest();
		userRequest.setFirstName("kaleab");
		userRequest.setLastName("tadewos");
		userRequest.setGender('m');
		userRequest.setEmail("tadewoskaleab@gmail.com");
		userRequest.setPassword("123");
		userRequest.setRoles(roles);
		
		userResponses.add(userResponse);
		
	}
	
//	@Test
//	void createUser_whenValidInput_thenReturns200() throws Exception {
//		mockMvc.perform(post("/users")
//			    .contentType("application/json"))
//			    .andExpect(status().isOk());
//	}
	
	@Test
	void getUsers_whenValidInput_thenReturns200() throws Exception {
		
		when(userService.findAll())
		.thenReturn(userResponses);
		
		MvcResult mvcResult = mockMvc.perform(get("/users"))
			    .andExpect(status().isOk())
			    .andReturn();
	    
		List<UserResponse> expectedResponseBody = userResponses; 
		
		String actualResponseBody = mvcResult.getResponse().getContentAsString();
		assertThat(actualResponseBody).isEqualToIgnoringWhitespace(
	              objectMapper.writeValueAsString(expectedResponseBody));
	}
	
	@Test
	void way2_getUsers_whenValidInput_thenReturns200() throws Exception {
		
		MvcResult mvcResult = mockMvc.perform(get("/users"))
			    .andExpect(status().isOk())
			    .andReturn();
		
		//just check if findAll is called by this controller.
		verify(userService).findAll();
	}
	
	@Test
	void createUser_whenValidInput_thenReturns200() throws Exception {
		
//		when(userService.save(userRequest))
//		.thenReturn(userResponse);
		
		//simulate making a request from controller.
		mockMvc.perform(post("/users")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(userRequest)))
				.andExpect(status().isOk());
	   
		//check that after the request is made , then controller do the appropriate action to do the task.
		ArgumentCaptor<UserRequest> userCaptor = ArgumentCaptor.forClass(UserRequest.class);
		verify(userService, times(1)).save(userCaptor.capture());
		assertThat(userCaptor.getValue().getFirstName()).isEqualTo("kaleab");
		assertThat(userCaptor.getValue().getLastName()).isEqualTo("tadewos");
	}
	
	@Test
	void updateById_whenValidInput_thenReturns200() throws Exception {
		
		//simulate making a request from controller.
		mockMvc.perform(put("/users/{userid}" , 1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(userRequest)))
				.andExpect(status().isOk());
	   
		//check that after the request is made , then controller do the appropriate action to do the task.
		ArgumentCaptor<UserRequest> userCaptor = ArgumentCaptor.forClass(UserRequest.class);
		verify(userService, times(1)).update(Mockito.eq(1) , userCaptor.capture());
		assertThat(userCaptor.getValue().getFirstName()).isEqualTo("kaleab");
		assertThat(userCaptor.getValue().getLastName()).isEqualTo("tadewos");
	}
	  
}
