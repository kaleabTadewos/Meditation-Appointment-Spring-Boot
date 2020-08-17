package app_checking.util.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Appointment;
import app_checking.domain.Location;
import app_checking.domain.User;
import app_checking.dto.AppointmentRequest;
import app_checking.dto.UserDTO;
import app_checking.service.LocationService;
import app_checking.service.UserService;

@Component
public class AppointmentRequestMapper {

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    CustomObjectMapper objectMapper;

    public Appointment appointmentBuilder(AppointmentRequest appointmentRequest) {

        UserDTO userDTO = userService.findById(appointmentRequest.getUserId());
        User user = objectMapper.getUserEntityFromDTO(userDTO);
        Appointment appointment = new Appointment();
        appointment.setDate(appointmentRequest.getDate());
        appointment.setUser(user);
        Location location = locationService.findById(appointmentRequest.getLocationId());
        appointment.setLocation(location);
        return appointment;
    }
}
