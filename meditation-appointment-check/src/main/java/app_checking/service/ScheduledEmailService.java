package app_checking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import app_checking.domain.User;

//Note :- ${scheduledEmailCron} :- this value can be fetched from application.yaml file.

@Service
public class ScheduledEmailService {
	
//	*/10 * * * * * Every ten second
//	0 * * * * * Every minute
	
	@Autowired
	EmailService emailService;
	
	@Autowired 
	ReservationService reservationService;
	
	@Value("${scheduledEmailCron}")
	private String scheduleParameter;
	
	
	@Scheduled(cron="${scheduledEmailCron}")
	public void sendEmailToComingAppointment() {
		
		LocalDate currentDate = LocalDate.now();
		
		List<User> users = reservationService.findAcceptedReservationsByDate(currentDate);
		
		for(User user : users) {
			String email = user.getEmail();
			//send(email);
		}
	}
	
	private void send(String toEmail) {
		String message = "You have a appointment in next 24 hours";
		String subject = "TM Checking appointment";
		
		emailService.sendMail(toEmail, subject, message);
		System.out.println("Email has sent");
		
	}
}
