package app_checking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app_checking.domain.Appointment;
import app_checking.dto.request.AppointmentRequest;
import app_checking.dto.response.AppointmentResponse;
import app_checking.repository.AppointmentRepository;
import app_checking.service.AppointmentService;
import app_checking.service.UserService;
import app_checking.util.mapper.entitytoresponsedto.AppointmentEntityToAppoinmentResponseMapper;
import app_checking.util.mapper.requestdtotoentity.AppointmentRequestToAppointmentEntityMapper;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
    AppointmentRepository apointmentRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private LocationServiceImpl locationService;

	@Autowired
	AppointmentRequestToAppointmentEntityMapper requestMapper;

	@Autowired
	protected AppointmentEntityToAppoinmentResponseMapper responseMapper;
	
	public AppointmentResponse save(AppointmentRequest appointmentRequest) {
		Appointment appointment = requestMapper.map(appointmentRequest);
		apointmentRepository.save(appointment);
		return responseMapper.map(appointment);
	}
	@Transactional(readOnly = true)
	public List<AppointmentResponse> findAll(){
		List<Appointment> appointments = apointmentRepository.findAll();
		return responseMapper.mapList(appointments);
	}
	@Transactional(readOnly = true)
	public AppointmentResponse findAppointmentResponseById(int appointmentid) {
		Optional<Appointment> appointment = apointmentRepository.findById(appointmentid);
		if(appointment.isPresent()) {
			return responseMapper.map(appointment.get());
		}
		else {
			return null;
		}
	}
	@Transactional(readOnly = true)
	public AppointmentResponse findById(int Appointmentid) {
		Optional<Appointment> appointment = apointmentRepository.findById(Appointmentid);
		if(appointment.isPresent()) {
			return responseMapper.map(appointment.get());
		}
		return null; 
	}
	
	public AppointmentResponse update(int appointmentId, AppointmentRequest newAppointment) {
		Optional<Appointment> appointment = apointmentRepository.findById(appointmentId);
		if(!appointment.isPresent()) {
			return null;
		}
		Appointment app = requestMapper.map(newAppointment);
		app.setId(appointmentId);
    	apointmentRepository.save(app);
    	return responseMapper.map(app);
	}
	
	public void delete(int appointmentId) {
		Optional<Appointment> appointment = apointmentRepository.findById(appointmentId);
		if(!appointment.isPresent()) {
			return;
		}
    	apointmentRepository.deleteById(appointmentId);
	}
}
