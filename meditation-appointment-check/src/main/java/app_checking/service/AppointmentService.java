package app_checking.service;


import org.springframework.stereotype.Service;

import app_checking.domain.Appointment;
import app_checking.dto.AppointmentRequest;
import app_checking.dto.AppointmentResponse;

import java.util.List;

@Service
public interface AppointmentService {

	public AppointmentResponse save(AppointmentRequest appointmentRequest);
	
	public List<AppointmentResponse> findAll();
	
	public AppointmentResponse findAppointmentResponseById(int appointmentid);
	
	public Appointment findById(int Appointmentid) ;
	
	public AppointmentResponse update(Appointment newAppointment);
	
	public void delete(int AppointmentId);

	public List<AppointmentResponse> convertEntityListToResponse(List<Appointment> userList);

	public AppointmentResponse convertEntityToResponse(Appointment appointment);

}
