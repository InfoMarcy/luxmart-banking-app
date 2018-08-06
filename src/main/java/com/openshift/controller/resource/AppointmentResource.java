package com.openshift.controller.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openshift.model.actividades.Cita;
import com.openshift.service.AppointmentService;

@RestController
@RequestMapping("api/v1/appointment")
@PreAuthorize("hasRole('ADMIN')")
public class AppointmentResource {

    @Autowired
    private AppointmentService appointmentService;

    @RequestMapping()
    public List<Cita> findAppointmentList() {
        List<Cita> appointmentList = appointmentService.getAppointmentList();

        return appointmentList;
    }

    @RequestMapping("/{username}/{id}/confirm")
    public void confirmAppointment(@PathVariable("username") String username, @PathVariable("id") String id) {
    	System.out.println(username + " id " + id);
    	appointmentService.getAppointmentById(username, id);
    }
}
