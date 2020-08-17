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
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
    private Date createdDate;
    
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
