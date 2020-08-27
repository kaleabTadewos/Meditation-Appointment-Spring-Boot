package app_checking.util.mapper.entitytoresponsedto;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Appointment;
import app_checking.dto.response.AppointmentResponse;
import app_checking.util.mapper.BaseMapper;

@Component
public class AppointmentEntityToAppoinmentResponseMapper extends BaseMapper<Appointment, AppointmentResponse>{

    @Autowired
    public AppointmentEntityToAppoinmentResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Appointment.class, AppointmentResponse.class);
    }

}
