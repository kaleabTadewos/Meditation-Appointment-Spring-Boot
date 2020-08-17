package app_checking.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;;

@Service
public class EmailService {
	
	@Value("${emailAddress}")
	private String emailAddress;

	@Autowired
	private JavaMailSender javaMailSender;
	
	public static int noOfQuickServiceThreads = 20;
	
	
	public EmailService() {}
	
	/* Here This is happening 
		- ScheduledExecutorService: The java.util.concurrent.ScheduledExecutorService is an 
		  ExecutorService which can schedule tasks to run after a delay, or to execute repeatedly 
		  with a fixed interval of time in between each execution. 
		  Tasks are executed asynchronously by a worker thread, and not by the thread handing the 
		  task to the ScheduledExecutorService. 
		- Below for example submit method is accepting a Runnable type task , so it will run it asynchronously.
		- While sending your email , your app gonna run.
	*/
	
	
	private ScheduledExecutorService quickService = Executors.newScheduledThreadPool(noOfQuickServiceThreads); // Creates a thread pool that reuses fixed number of threads(as specified by noOfThreads in this case).
	
	
	public void sendMail(String toEmail, String subject, String message) throws MailException,RuntimeException {
		
	   SimpleMailMessage msg = new SimpleMailMessage();
	   
	   msg.setTo(emailAddress, toEmail);
	   msg.setSubject(subject);
	   msg.setText(message);
	   
	   quickService.submit(new Runnable() {
	      @Override
	      public void run() {
	         try{
	            javaMailSender.send(msg);
	         }
	         catch(Exception e){
	        	 System.out.println(e);
	         }
	      }
	   });
	}
	
	
	
	
}
