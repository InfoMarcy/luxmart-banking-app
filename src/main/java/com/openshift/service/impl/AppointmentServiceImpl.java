package com.openshift.service.impl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openshift.model.User;
import com.openshift.model.actividades.Cita;
import com.openshift.service.AppointmentService;
import com.openshift.service.UserService;

@Service("appointmentService")
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	UserService userService;

	// create appointment
	@Override
	public void createAppointment(Cita appointment, Principal principal) {
		// get the current user
		User user = userService.findByEmail(principal.getName());
		// set the username for the appointment
		appointment.setUsername(user.getUsername());
		// add the appointment to the user
		user.getCitas().add(appointment);

		// save the appointment
		userService.save(user);
	}

	// get the list of all the appointments
	@Override
	public List<Cita> getAppointmentList() {

		// find all users
		List<User> users = (List<User>) userService.findAll();
		// create a new arrayList of appointments
		List<Cita> appointments = new ArrayList<>();

		// iterate over the list of all the users
		for (Iterator<User> user = users.iterator(); user.hasNext();) {
			// get the user from the forEach
			User getUser = user.next();

			// iterate over the AppointmentList
			for (Cita item : getUser.getCitas()) {

				// check if the class is not empty
				if (item != null) {
					appointments.add(item);
				}

			}

		}

		return appointments;
	}

	// confirm an appointment
	@Override
	public Cita getAppointmentById(String username, String id) {
		User user = userService.findByUsername(username);

		// iterate over the Appointments
		for (Cita item : user.getCitas()) {
			// check if the appointment is the one we are looking for
			if (item.getId().toString().equals(id)) {
				item.setConfirmed(true);

				user.getCitas().remove(item);
				user.getCitas().add(item);

				userService.save(user);
			}

		}

		return null;
	}

}
