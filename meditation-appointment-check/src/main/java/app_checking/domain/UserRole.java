package app_checking.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
//
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
public class UserRole {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Enumerated(EnumType.STRING)
	private UserRoles userRoles;
	
	
	//@CreationTimestamp : used to store current date as default value for this property. 
	//so it only created once in contrast with @UpdatedTimestamp.
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
    private Date createdDate;
    
	//@Timestamp : hibernate automatically map data type to sql , but sometimes you need to specify
	//	i.e Date can be saved as sate , time or timestamp so be specific ad choose i.e timeStamp.
	//	here TimeStamp is selected.
	//what is the differnce between TimeStamp and datetime ?
	//	The difference is while saving timestamp into database it converted to UTC and whenever it fetched
	//	from database it convert back to the current time zone on running server.
	//	this is not the case for Datetime. but using datetime you can access all date related methods.
	
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private Date updatedDate;
    
    
    public UserRole() {
    }


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}



	public UserRoles getUserRoles() {
		return userRoles;
	}


	public void setUserRoles(UserRoles userRoles) {
		this.userRoles = userRoles;
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
