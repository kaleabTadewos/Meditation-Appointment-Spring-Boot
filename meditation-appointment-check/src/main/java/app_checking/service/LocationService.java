package app_checking.service;

import java.util.List;

import app_checking.domain.Location;
import app_checking.dto.response.LocationResponse;

public interface LocationService {

	public LocationResponse save(Location location);
	
	public List<LocationResponse> findAll();
	
	public LocationResponse findLocationResponseById(int locationid);
	
	public Location findById(int locationid);
	
	public LocationResponse update(Location location);
	
	public void delete(int locationId);
	public List<LocationResponse> convertEntityListToResponse(List<Location> locationList);
	public LocationResponse convertEntityToResponse(Location locationList);

}
