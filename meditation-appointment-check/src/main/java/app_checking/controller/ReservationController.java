package app_checking.controller;

import app_checking.domain.Reservation;
import app_checking.domain.User;
import app_checking.dto.request.ReservationRequest;
import app_checking.dto.response.ReservationResponse;
import app_checking.dto.response.UserResponse;
import app_checking.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping()
    public ReservationResponse createReservation(@RequestBody ReservationRequest reservationRequest) {
    	return reservationService.save(reservationRequest);
    }
    
    @GetMapping()
    public List<ReservationResponse> getReservations(){
    	return reservationService.findAll();
    }
    
    @GetMapping("/{reservationid}")
    public ReservationResponse getReservationById(@PathVariable int reservationid) {
    	return reservationService.findReservationResponseById(reservationid);
    }
    
    @PutMapping("/{reservationid}")
    public ReservationResponse updateById(@PathVariable int reservationid, @RequestBody ReservationRequest reservation) {
    	return reservationService.update(reservationid, reservation);
    }
    
    @DeleteMapping("/{reservationid}")
    public void deleteReservation(@PathVariable int reservationid) {
    	reservationService.delete(reservationid);
    }
    
    @GetMapping(value = "/scheduled")
    public List<UserResponse> triggerScheduled() throws ParseException {
		
    	return reservationService.findAcceptedReservationsByDate(LocalDate.now());
    	
    }
    
    
}
