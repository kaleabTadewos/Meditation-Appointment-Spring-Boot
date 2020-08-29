package app_checking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import app_checking.domain.Role;
import app_checking.domain.User;
import app_checking.domain.UserRoles;
import app_checking.repository.UserRepository;

//By default spring configure test to happen on in memory database.
//If i want to make a test on current database then use this annotation: @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//But if i want to make the test on in memory database then just include h2 database dependency. and since spring by default configure in memory it will work.


@ExtendWith(SpringExtension.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
    public void testfindByEmail() {
		
		// setup data scenario
		User user = new User();
		user.setEmail("kaleabero@gmail.com");
        
		// save test data
		entityManager.persist(user);

        // Find an inserted record
		User foundUser = userRepository.findByEmail("kaleabero@gmail.com").get(0);
        
        assertEquals(foundUser.getEmail(), "kaleabero@gmail.com");
    }
	
	@Test
    public void testfindWithRoles() {
		
		Role role = new Role();
		role.setUserRoles(UserRoles.ADMIN);
		
		List<Role> roles = new ArrayList<>();
		roles.add(role);
		
		User user1 = new User();
		User user2 = new User();
		User user3 = new User();
		
		user1.setRole(roles);
		user2.setRole(roles);
        
		// save test data
		entityManager.persist(user1);
		entityManager.persist(user2);
		entityManager.persist(user3);

        // Find an inserted record
		List<User> foundUser = userRepository.findWithRoles();
        
        assertNotNull(foundUser);
		assertEquals(foundUser.size(), 3);
    }
}
