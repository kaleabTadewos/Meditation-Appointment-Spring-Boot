package app_checking.util.mapper.requestdtotoentity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Appointment;
import app_checking.domain.Location;
import app_checking.domain.User;
import app_checking.dto.request.AppointmentRequest;
import app_checking.repository.LocationRepository;
import app_checking.repository.UserRepository;
import app_checking.util.mapper.BaseMapper;
import ma.glasnost.orika.MapperFactory;

@Component
public class AppointmentRequestToAppointmentEntityMapper extends BaseMapper<AppointmentRequest , Appointment> {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
    public AppointmentRequestToAppointmentEntityMapper(MapperFactory mapperFactory){
        super(mapperFactory, AppointmentRequest.class, Appointment.class);
    }
	

	@Override
	public Appointment map(AppointmentRequest appointmentRequest) {
	
	try
      {
			Appointment result = mapper.map(appointmentRequest);
			User user = userRepository.findById(appointmentRequest.getUserId()).get();
			Location location = locationRepository.findById(appointmentRequest.getLocationId()).get();
			
			result.setUser(user);
			result.setLocation(location);
			return result;
      }
    catch(Exception exc)
      {
          exc.printStackTrace();
          return null;
      }
	}

//    public Appointment appointmentBuilder(AppointmentRequest appointmentRequest) {
//
//        UserDTO userDTO = userService.findById(appointmentRequest.getUserId());
//        User user = objectMapper.getUserEntityFromDTO(userDTO);
//        Appointment appointment = new Appointment();
//        appointment.setDate(appointmentRequest.getDate());
//        appointment.setUser(user);
//        Location location = locationService.findById(appointmentRequest.getLocationId());
//        appointment.setLocation(location);
//        return appointment;
//    }
}

