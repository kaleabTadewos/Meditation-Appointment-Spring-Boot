package app_checking.util.mapper;


import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app_checking.domain.Location;
import app_checking.dto.LocationResponse;

@Component
public class LocationResponseMapper extends BaseMapper<Location, LocationResponse> {
    @Autowired
    public LocationResponseMapper(MapperFactory mapperFactory){
        super(mapperFactory, Location.class, LocationResponse.class);
    }

}
