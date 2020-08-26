package app_checking.util.mapper.requestdtotoentity;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Role;
import app_checking.domain.User;
import app_checking.dto.request.UserRequest;
import app_checking.util.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;

@Component
public class UserRequestToUserEntityMapper extends BaseMapper<UserRequest , User> {
	
	@Autowired
    public UserRequestToUserEntityMapper(MapperFactory mapperFactory){
        super(mapperFactory, UserRequest.class, User.class);
    }
	
	@Override
	public User map(UserRequest userRequest) {
	
	try
      {
			User result = mapper.map(userRequest);
			
			List<Role> roles = Arrays.stream(userRequest.getRoles()).map(r -> {
				Role role = new Role();
				role.setUserRoles(r);
				return role;
			}).collect(Collectors.toList());
			result.setRole(roles);
			return result;
      }
    catch(Exception exc)
      {
          exc.printStackTrace();
          return null;
      }
	}
	
}
