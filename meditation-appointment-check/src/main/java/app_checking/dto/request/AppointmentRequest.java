package app_checking.dto.request;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AppointmentRequest {

    @NotBlank(message = "appointmentDate is mandatory")
    private Date date;
  
    @NotBlank(message = "userId is mandatory")
    private int userId;
    @NotBlank(message = "locationId is mandatory")
    private int locationId;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
