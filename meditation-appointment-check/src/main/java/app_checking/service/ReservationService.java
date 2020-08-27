package app_checking.service;

import org.springframework.stereotype.Service;

import app_checking.dto.request.ReservationRequest;
import app_checking.dto.response.ReservationResponse;
import app_checking.dto.response.UserResponse;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ReservationService {

	
	public ReservationResponse save(ReservationRequest reservationRequest);
	
	public List<ReservationResponse> findAll();
	
	public ReservationResponse findReservationResponseById(int reservationid);
	
	public ReservationResponse findById(int reservationId);
	
	public ReservationResponse update(int reservationId, ReservationRequest newReservation);
	
	public void delete(int ReservationId);
	
	public List<UserResponse> findAcceptedReservationsByDate(LocalDate date);

}
