package app_checking.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app_checking.domain.User;
import app_checking.repository.UserRepository;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {
    
	private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
    	
    	User user = this.userRepository.findByUserName(s).get(0);
        UserPrincipal userPrincipal = new UserPrincipal(user);

        return userPrincipal;
        
    }
}