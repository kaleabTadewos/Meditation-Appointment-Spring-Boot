package app_checking.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app_checking.domain.Reservation;
import app_checking.domain.User;
import app_checking.dto.request.ReservationRequest;
import app_checking.dto.response.ReservationResponse;
import app_checking.dto.response.UserResponse;
import app_checking.repository.ReservationRepository;
import app_checking.service.EmailService;
import app_checking.service.ReservationService;
import app_checking.service.UserService;
import app_checking.util.mapper.entitytoresponsedto.ReservationEntityToReservationResponseMapper;
import app_checking.util.mapper.entitytoresponsedto.UserEntityToUserResponseMapper;
import app_checking.util.mapper.requestdtotoentity.ReservationRequestToReservationEntityMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
    ReservationRepository reservationRepository;
	
	@Autowired
    UserService userService;
	
	@Autowired
	AppointmentServiceImpl appointmentService;
	
	@Autowired
	EmailService emailService;

	@Autowired
	protected ReservationEntityToReservationResponseMapper responseMapper;
	
	@Autowired
	protected ReservationRequestToReservationEntityMapper requestMapper;
	
	@Autowired
	protected UserEntityToUserResponseMapper userResponseMapper;
	
	public ReservationResponse save(ReservationRequest reservationRequest) {
		Reservation reservation = requestMapper.map(reservationRequest);
		reservationRepository.save(reservation);
		
		//emailService.sendMail(user.getEmail(), "TM Checker System", "You reservation successfully recorded");
		
		return responseMapper.map(reservation);
	}

	@Transactional(readOnly = true)
	public List<ReservationResponse> findAll(){
		List<Reservation> reservations = reservationRepository.findAll();
		return responseMapper.mapList(reservations);
	}

	@Transactional(readOnly = true)
	public ReservationResponse findReservationResponseById(int reservationid) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationid);

		if(reservation.isPresent()) {
			return responseMapper.map(reservation.get());
		}
		else{
			return null;
		}
	}

	@Transactional(readOnly = true)
	public ReservationResponse findById(int reservationId) {
		Optional<Reservation> reservation = reservationRepository.findById(reservationId);
		return reservation.isPresent() ? responseMapper.map(reservation.get()) : null; 
	}
	
	public ReservationResponse update(int reservationId, ReservationRequest newReservation) {
		Optional<Reservation> oldReservation = reservationRepository.findById(reservationId);
    	if(!oldReservation.isPresent()){
    		return null;
    	}
    	
    	Reservation reservation = oldReservation.get();
    	
    	reservationRepository.save(reservation);
    	
//    	if(oldReservation.getStatus().equals(ReservationStatus.ACCEPTED)) {
//    		String toEmail = oldReservation.getConsumer().getEmail();
//    		emailService.sendMail(toEmail, "TM Checker System", "Your reservation got ACCEPTED");
//    		
//			List<Reservation> res_list = reservationRepository.getAllReservationsByAppointmentId(oldReservation.getAppointment().getId());
//    		
//			System.out.println(res_list);
//    		
//    		
//			for(Reservation reservation : res_list) {
//    			System.out.println(reservation.getId());
//    			System.out.println(oldReservation.getId());
//    			
//    			
//    			if(reservation.getId() == oldReservation.getId()) {
//    				continue;
//    			}
//    			reservation.setStatus(ReservationStatus.DECLINED);
//    		}
//    	}
//    	if(oldReservation.getStatus().equals(ReservationStatus.DECLINED)) {
//    		String toEmail = oldReservation.getConsumer().getEmail();
//    		emailService.sendMail(toEmail, "TM Checker System", "Your reservation got DECLINED");
//    	}
    	
    	
    	return responseMapper.map(reservation);
	}
	
	public void delete(int ReservationId) {
		Optional<Reservation> oldReservation = reservationRepository.findById(ReservationId);
    	if(!oldReservation.isPresent()){
    		return;
    	}
    	reservationRepository.deleteById(ReservationId);
	}

	public List<UserResponse> findAcceptedReservationsByDate(LocalDate date) {
		List<User> users = reservationRepository.findAcceptedReservationsByAppointmentDate(date);
		return userResponseMapper.mapList(users);
	}
}
