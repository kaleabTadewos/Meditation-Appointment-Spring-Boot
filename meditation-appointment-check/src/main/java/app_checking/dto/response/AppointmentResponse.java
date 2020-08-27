package app_checking.dto.response;

import java.io.Serializable;
import java.util.Date;

public class AppointmentResponse implements Serializable {
	
	private int id;
	
	private Date date;
	
    private Date createdDate;
    
    private Date updatedDate;

    public AppointmentResponse(){

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
}
