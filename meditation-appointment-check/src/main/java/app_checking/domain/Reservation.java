package app_checking.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
public class Reservation {

	@Id
	@GeneratedValue
	private int id;
	
	//by default hibernate map enum into numbers so to make String instead use @Enumerated(EnumType.STRING)

	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private ReservationStatus status;

	@ManyToOne
	@JoinColumn(name="consumer_id")
	private User consumer;

	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name="appointment_id")
	private Appointment appointment;



	public Reservation() {

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public ReservationStatus getStatus() {
		return status;
	}



	public void setStatus(ReservationStatus status) {
		this.status = status;
	}


	public User getConsumer() {
		return consumer;
	}



	public void setConsumer(User consumer) {
		this.consumer = consumer;
	}


	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	public Appointment getAppointment() {
		return appointment;
	}


	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}
	
	
	

}
