package app_checking.dto.request;

import javax.validation.constraints.NotBlank;

import app_checking.domain.ReservationStatus;

public class ReservationRequest {

	@NotBlank(message = "consumer_id is mandatory")
    private int consumer_id;
	@NotBlank(message = "appointmentId is mandatory")
    private int appointment_id;

	public int getConsumer_id() {
		return consumer_id;
	}

	public void setConsumer_id(int consumer_id) {
		this.consumer_id = consumer_id;
	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}
    
    
}
