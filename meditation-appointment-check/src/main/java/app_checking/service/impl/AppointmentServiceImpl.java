//package app_checking.service.impl;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import app_checking.domain.Appointment;
//import app_checking.dto.AppointmentRequest;
//import app_checking.dto.AppointmentResponse;
//import app_checking.repository.AppointmentRepo;
//import app_checking.service.AppointmentService;
//import app_checking.service.UserService;
//import app_checking.util.mapper.AppointmentRequestMapper;
//import app_checking.util.mapper.AppointmentResponseMapper;
//import app_checking.util.mapper.CustomObjectMapper;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class AppointmentServiceImpl implements AppointmentService {
//	
//	@Autowired
//    AppointmentRepo apointmentRepository;
//
//	@Autowired
//	private UserService userService;
//
//	@Autowired
//	private LocationServiceImpl locationService;
//
//	@Autowired
//	CustomObjectMapper objectMapper;
//
//	@Autowired
//	protected AppointmentResponseMapper responseMapper;
//
//	@Autowired
//	protected AppointmentRequestMapper appointmentRequestMapper;
//	
//	public AppointmentResponse save(AppointmentRequest appointmentRequest) {
//
//		Appointment appointment = appointmentRequestMapper.appointmentBuilder(appointmentRequest);
//		apointmentRepository.save(appointment);
//		return convertEntityToResponse(appointment);
//	}
//	@Transactional(readOnly = true)
//	public List<AppointmentResponse> findAll(){
//		List<Appointment> appointments = apointmentRepository.findAll();
//		return convertEntityListToResponse(appointments);
//	}
//	@Transactional(readOnly = true)
//	public AppointmentResponse findAppointmentResponseById(int appointmentid) {
//		Optional<Appointment> appointment = apointmentRepository.findById(appointmentid);
//		if(appointment.isPresent()) {
//			return convertEntityToResponse(appointment.get());
//		}
//		else {
//			return null;
//		}
//	}
//	@Transactional(readOnly = true)
//	public Appointment findById(int Appointmentid) {
//		Optional<Appointment> Appointment = apointmentRepository.findById(Appointmentid);
//		return Appointment.isPresent() ? Appointment.get(): null; 
//	}
//	
//	public AppointmentResponse update(Appointment newAppointment) {
//		Appointment oldAppointment = findById(newAppointment.getId());
//    	if(oldAppointment == null){
//    		return null;
//    	}
//    	oldAppointment.setDate(newAppointment.getDate());
//    	oldAppointment.setUpdatedDate(new Date());
//    	apointmentRepository.save(oldAppointment);
//    	return convertEntityToResponse(oldAppointment);
//	}
//	
//	public void delete(int AppointmentId) {
//		Appointment oldAppointment = findById(AppointmentId);
//    	if(oldAppointment == null){
//    		return;
//    	}
//    	apointmentRepository.deleteById(AppointmentId);
//	}
//
//	@Override
//	@Transactional(propagation=Propagation.SUPPORTS)
//	public List<AppointmentResponse> convertEntityListToResponse(List<Appointment> appointmentList) {
//		if(null == appointmentList){
//			return null;
//		}
//		else {
//			return appointmentList.stream()
//					.map(responseMapper::map)
//					.collect(Collectors.toList());
//		}
//	}
//
//	@Override
//	@Transactional(propagation=Propagation.SUPPORTS)
//	public AppointmentResponse convertEntityToResponse(Appointment appointment) {
//		return responseMapper.map(appointment);
//	}
//
//}
