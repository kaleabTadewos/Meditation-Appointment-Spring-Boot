package app_checking.util.mapper.requestdtotoentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Appointment;
import app_checking.domain.Reservation;
import app_checking.domain.ReservationStatus;
import app_checking.domain.User;
import app_checking.dto.request.ReservationRequest;
import app_checking.repository.AppointmentRepository;
import app_checking.repository.UserRepository;
import app_checking.util.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;

@Component
public class ReservationRequestToReservationEntityMapper extends BaseMapper<ReservationRequest , Reservation> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
    public ReservationRequestToReservationEntityMapper(MapperFactory mapperFactory){
        super(mapperFactory, ReservationRequest.class, Reservation.class);
    }
	

	@Override
	public Reservation map(ReservationRequest reservationRequest) {
	
	try
      {
			Reservation result = mapper.map(reservationRequest);
			User user = userRepository.findById(reservationRequest.getConsumer_id()).get();
			Appointment appointment = appointmentRepository.findById(reservationRequest.getAppointment_id()).get();
			
			result.setStatus(ReservationStatus.PENDING);
			result.setConsumer(user);
			result.setAppointment(appointment);
			return result;
      }
    catch(Exception exc)
      {
          exc.printStackTrace();
          return null;
      }
	}
}

