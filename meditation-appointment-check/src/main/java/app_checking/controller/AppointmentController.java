package app_checking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import app_checking.dto.request.AppointmentRequest;
import app_checking.dto.response.AppointmentResponse;
import app_checking.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping()
    public AppointmentResponse createAppointment(@RequestBody AppointmentRequest appointmentRequest) {

    	return appointmentService.save(appointmentRequest);
    }
    
    @GetMapping()
    public List<AppointmentResponse> getAppointments(){
    	return appointmentService.findAll();
    }
    
    @GetMapping("/{appointmentid}")
    public AppointmentResponse getAppointmentById(@PathVariable int appointmentid) {
    	return appointmentService.findAppointmentResponseById(appointmentid);
    }
    
    @PutMapping("/{appointmentid}")
    public AppointmentResponse updateById(@PathVariable int appointmentid , @RequestBody AppointmentRequest appointment) {
    	return appointmentService.update(appointmentid , appointment);
    }
    
    @DeleteMapping("/{appointmentid}")
    public void deleteAppointment(@PathVariable int appointmentid) {
    	appointmentService.delete(appointmentid);
    }
    
    
}
