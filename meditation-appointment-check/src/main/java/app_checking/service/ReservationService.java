//package app_checking.service;
//
//import org.springframework.stereotype.Service;
//
//import app_checking.domain.Reservation;
//import app_checking.domain.User;
//import app_checking.dto.ReservationRequest;
//import app_checking.dto.ReservationResponse;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public interface ReservationService {
//
//	
//	public ReservationResponse save(ReservationRequest reservationRequest);
//	
//	public List<ReservationResponse> findAll();
//	
//	public ReservationResponse findReservationResponseById(int reservationid);
//	
//	public Reservation findById(int reservationId);
//	
//	public ReservationResponse update(int reservationId, Reservation newReservation);
//	
//	public void delete(int ReservationId);
//	
//	public List<User> findAcceptedReservationsByDate(LocalDate date);
//	
//	public List<ReservationResponse> convertEntityListToResponsePage(List<Reservation> reservationList);
//
//	public ReservationResponse convertEntityToResponse(Reservation reservation);
//
//}
