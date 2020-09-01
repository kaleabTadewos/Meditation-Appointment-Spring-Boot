package app_checking.domain;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Appointment {

	@Id
	@GeneratedValue
	private int id;
	

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;


	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	@Column(updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date updatedDate;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy="appointment", cascade = CascadeType.REMOVE)
	private List<Reservation> reservations;

	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;

	public Appointment() {

	}

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
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



	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	//Here what happen is this:
	// Appointment and Reservation have Bi Directional Association , so we have to make sure this association persisted.
	// so whenever a new reservation added to a reservation - it should also know this appointment as its appointment.
	public void addReservation(Reservation reservation) {
		reservation.setAppointment(this);
		this.reservations.add(reservation);
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	};

}
