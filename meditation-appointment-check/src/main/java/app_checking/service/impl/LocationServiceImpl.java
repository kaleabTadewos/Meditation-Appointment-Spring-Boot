package app_checking.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import app_checking.domain.Location;
import app_checking.dto.LocationResponse;
import app_checking.repository.LocationRepo;
import app_checking.service.LocationService;
import app_checking.util.mapper.LocationResponseMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class LocationServiceImpl implements LocationService {
	
	@Autowired
    LocationRepo locationRepository;

	@Autowired
	protected LocationResponseMapper responseMapper;

	public LocationResponse save(Location location) {
		
		locationRepository.save(location);
		return convertEntityToResponse(location);
	}

	@Transactional(readOnly = true)
	public List<LocationResponse> findAll(){
		List<Location> locations = locationRepository.findAll();
		return convertEntityListToResponse(locations);
	}

	@Transactional(readOnly = true)
	public LocationResponse findLocationResponseById(int locationid) {
		Optional<Location> location = locationRepository.findById(locationid);
		LocationResponse locationResponse = new LocationResponse();
		if(location.isPresent()) {
			Location location1 = location.get();
			return convertEntityToResponse(location1);
		}
		else {
			return null;
		}
	}

	@Transactional(readOnly = true)
	public Location findById(int locationid) {
		Optional<Location> location = locationRepository.findById(locationid);
		return location.isPresent() ? location.get() : null;
	}
	
	public LocationResponse update(Location newlocation) {
		Location oldLocation = findById(newlocation.getId());
    	if(oldLocation == null){
    		return null;
    	}
    	oldLocation.setRoomNo(newlocation.getRoomNo());
    	oldLocation.setBuildNo(newlocation.getBuildNo());
    	oldLocation.setCity(newlocation.getCity());
    	oldLocation.setState(newlocation.getState());
    	oldLocation.setStreet(newlocation.getStreet());
    	oldLocation.setZipcode(newlocation.getZipcode());
    	locationRepository.save(oldLocation);
    	return convertEntityToResponse(oldLocation);
	}
	
	public void delete(int locationId) {
		Location oldLocation = findById(locationId);
    	if(oldLocation == null){
    		return;
    	}
    	locationRepository.deleteById(locationId);
	}

	@Override
	@Transactional(propagation= Propagation.SUPPORTS)
	public List<LocationResponse> convertEntityListToResponse(List<Location> locationList) {
		if(null == locationList){
			return null;
		}
		else {
			return locationList.stream()
					.map(responseMapper::map)
					.collect(Collectors.toList());
		}
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public LocationResponse convertEntityToResponse(Location location) {
		return responseMapper.map(location);
	}

}
