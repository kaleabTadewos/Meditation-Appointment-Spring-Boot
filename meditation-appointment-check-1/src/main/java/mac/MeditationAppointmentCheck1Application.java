package mac;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "mac"})
public class MeditationAppointmentCheck1Application {

	public static void main(String[] args) {
		SpringApplication.run(MeditationAppointmentCheck1Application.class, args);
	}

}
