package app_checking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@SpringBootApplication
public class MeditationAppointmentCheckApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeditationAppointmentCheckApplication.class, args);
	}
	
//	@Bean
//    public MapperFactory mapperFactory(){
//        return new DefaultMapperFactory.Builder().build();
//    }

}
