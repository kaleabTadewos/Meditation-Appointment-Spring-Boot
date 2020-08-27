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
	
	@Query(value = "SELECT consumer FROM Reservation re WHERE date(re.appointment.date) >= date(:date) AND date(re.appointment.date) <= date(:date)  AND re.status = 'ACCEPTED'")
	public List<User> findAcceptedReservationsByAppointmentDate(@Param("date") LocalDate date);
	
	@Query(value="FROM Reservation re WHERE re.appointment.id = :app_id")
	public List<Reservation> getAllReservationsByAppointmentId(@Param("app_id") int appointmentId);
	
}
