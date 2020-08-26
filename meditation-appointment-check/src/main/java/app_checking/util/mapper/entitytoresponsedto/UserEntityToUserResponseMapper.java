package app_checking.util.mapper.entitytoresponsedto;


import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.User;
import app_checking.dto.response.UserResponse;
import app_checking.util.mapper.BaseMapper;

@Component
public class UserEntityToUserResponseMapper extends BaseMapper<User, UserResponse>{

    @Autowired
    public UserEntityToUserResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, User.class, UserResponse.class);
    }
}
