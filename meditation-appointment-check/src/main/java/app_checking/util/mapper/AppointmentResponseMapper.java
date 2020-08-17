package app_checking.util.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Appointment;
import app_checking.dto.AppointmentResponse;

@Component
public class AppointmentResponseMapper extends BaseMapper<Appointment, AppointmentResponse>{

    @Autowired
    public AppointmentResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Appointment.class, AppointmentResponse.class);
    }

}
