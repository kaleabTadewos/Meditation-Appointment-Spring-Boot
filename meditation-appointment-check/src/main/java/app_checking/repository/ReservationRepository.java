package app_checking.repository;
import app_checking.domain.Reservation;
import app_checking.domain.User;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
Note
 - @Transaction annotation tells spring to wrap this repo with transaction.
 - @Repository - to specify this is a bean. just meaningfull bean naming.
 - @Query :-  use to run a data using JQL , if you want to run SQL instead just add this ", nativeQuery = true".
 - for @Query parameters can be passed whenever you make a call to annotated method.
*/

@Repository
@Transactional
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
	
	//Inferred Queries : UserEntity findByName(String name);
	// queries like this are short so Spring Data know what to do. so we don't have to tell anything to it. it just create a query for us.
	
	//but for longer method name like the below queries : write your own custom JPQL query using @Query annotations.
	
	//Note that : Spring Data give us validity check as soon as you do this. If they are not write , you gonna have an exception. so they guaranted you are writing working query.
	
	
	@Query(value = "SELECT consumer FROM Reservation re WHERE date(re.appointment.date) >= date(:date) AND date(re.appointment.date) <= date(:date)  AND re.status = 'ACCEPTED'")
	public List<User> findAcceptedReservationsByAppointmentDate(@Param("date") LocalDate date);
	
	@Query(value="FROM Reservation re WHERE re.appointment.id = :app_id")
	public List<Reservation> getAllReservationsByAppointmentId(@Param("app_id") int appointmentId);
	
	
	//Note 
	//Inferred Queries : Sample => UserEntity findByName(String name);
	//Custom JPQL Queries with @Query : Sample => @Query("select u from UserEntity u where u.name = :name")
	//											  UserEntity findByNameCustomQuery(@Param("name") String name);
	//Native Queries with @Query : Sample => @Query(
	//  											value = "select * from user as u where u.name = :name",
	//  											nativeQuery = true)
	//												UserEntity findByNameNativeQuery(@Param("name") String name);
	
	//For Native Queries :- Since the query may contain database-specific SQL, there’s no way Spring Data or Hibernate can know what to check for
		//native queries may also differ among databases , so it may not work for all type of databases and also in memory databases.
	
	
	
}
