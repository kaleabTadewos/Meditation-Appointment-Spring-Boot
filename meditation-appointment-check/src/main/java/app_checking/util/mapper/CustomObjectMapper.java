//package app_checking.util.mapper;
//
//import org.apache.commons.beanutils.BeanUtils;
//import org.springframework.stereotype.Component;
//
//import app_checking.domain.User;
//import app_checking.domain.UserRole;
//import app_checking.domain.UserRoles;
//import app_checking.dto.UserDTO;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Component
//public class CustomObjectMapper {
//
//    public User getUserEntityFromDTO(UserDTO dto) {
//        try
//        {
//            User result = new User();
//            BeanUtils.copyProperties(result,dto);
//            List<UserRole> roles = Arrays.stream(dto.getRoles()).map(role -> {
//                UserRole rle = new UserRole();
//                rle.setUserRoles(role);
//                return rle;
//            }).collect(Collectors.toList());
//            result.setRole(roles);
//            return result;
//        }
//        catch(Exception exc)
//        {
//            exc.printStackTrace();
//            return null;
//        }
//    }
//
//    public UserDTO getUserDTOFromEntity(User usr){
//        try
//        {
//            UserDTO result = new UserDTO();
//            BeanUtils.copyProperties(result,usr);
//            if(usr.getRole() != null && !usr.getRole().isEmpty())
//            {
//                UserRoles[] roles = usr.getRole().stream().map(role -> role.getUserRoles()).collect(Collectors.toList()).toArray(new UserRoles[usr.getRole().size()]);
//                result.setRoles(roles);
//            }
//            result.setPassword(null);
//            return result;
//        }
//        catch(Exception exc)
//        {
//            exc.printStackTrace();
//            return null;
//        }
//    }
//}
