//package app_checking.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import app_checking.domain.User;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///*Here 
// 	"left join" is used to get User data only not other related associations like Appointment.
//*/
//
//@Repository
//@Transactional
//public interface UserRepository extends JpaRepository<User, Integer> {
//    @Query(value = "SELECT u from User u left join fetch u.role where u.email = :email")
//    List<User> findByEmail(String email);
//
//    @Query(value = "SELECT u from User u left join fetch u.role")
//    List<User> findWithRoles();
//
//}
