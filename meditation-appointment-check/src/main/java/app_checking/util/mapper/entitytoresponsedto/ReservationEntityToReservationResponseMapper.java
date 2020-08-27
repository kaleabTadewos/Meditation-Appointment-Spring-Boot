package app_checking.util.mapper.entitytoresponsedto;


import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Reservation;
import app_checking.dto.response.ReservationResponse;
import app_checking.util.mapper.BaseMapper;

@Component
public class ReservationEntityToReservationResponseMapper extends BaseMapper<Reservation, ReservationResponse>{

    @Autowired
    public ReservationEntityToReservationResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Reservation.class, ReservationResponse.class);
    }
}
