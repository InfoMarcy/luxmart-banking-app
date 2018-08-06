package com.openshift.service;

import java.security.Principal;
import java.util.List;

import com.openshift.model.actividades.Cita;

public interface AppointmentService {


	void createAppointment(Cita appointment, Principal principal);
	List<Cita> getAppointmentList();
	Cita getAppointmentById(String username, String id);

}
