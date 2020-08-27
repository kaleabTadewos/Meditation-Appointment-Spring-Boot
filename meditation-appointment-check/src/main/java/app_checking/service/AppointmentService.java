package app_checking.service;

import org.springframework.stereotype.Service;

import app_checking.dto.request.AppointmentRequest;
import app_checking.dto.response.AppointmentResponse;

import java.util.List;

@Service
public interface AppointmentService {

	public AppointmentResponse save(AppointmentRequest appointmentRequest);
	
	public List<AppointmentResponse> findAll();
	
	public AppointmentResponse findAppointmentResponseById(int appointmentid);
	
	public AppointmentResponse findById(int Appointmentid) ;
	
	public AppointmentResponse update(int appointmentId, AppointmentRequest newAppointment);
	
	public void delete(int AppointmentId);

}
