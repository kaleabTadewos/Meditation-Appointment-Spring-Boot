package app_checking.controller;

import app_checking.domain.Location;
import app_checking.dto.response.LocationResponse;
import app_checking.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping()
    public LocationResponse createLocation(@RequestBody Location location) {
    	return locationService.save(location);
    }
    
    @GetMapping()
    public List<LocationResponse> getLocations(){
    	return locationService.findAll();
    }
    
    @GetMapping("/{locationid}")
    public LocationResponse getLocationById(@PathVariable int locationid) {
    	return locationService.findLocationResponseById(locationid);
    }
    
    @PutMapping()
    public LocationResponse updateById(@RequestBody Location location) {
    	return locationService.update(location);
    }
    
    @DeleteMapping("/{locationid}")
    public void deleteLocation(@PathVariable int locationid) {
    	locationService.delete(locationid);
    }
    
    
}
