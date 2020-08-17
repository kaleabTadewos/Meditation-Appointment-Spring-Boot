package app_checking.dto;

import java.io.Serializable;
import java.util.Date;

import app_checking.domain.ReservationStatus;

public class ReservationResponse implements Serializable {

	private int id;
	
	private ReservationStatus status;
	
//	private Date reservationDate;
	
    private Date createdDate;
    
    private Date updatedDate;

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

//	public Date getReservationDate() {
//		return reservationDate;
//	}
//
//	public void setReservationDate(Date reservationDate) {
//		this.reservationDate = reservationDate;
//	}

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

}
