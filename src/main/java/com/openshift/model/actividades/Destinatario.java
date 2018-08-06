package com.openshift.model.actividades;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Destinatario {

	
	@Id	
	private String id;
	public Destinatario() {
		super();
		this.id = UUID.randomUUID().toString();
	}

	private String nombre;
	private String email;
	private String telefono;
	private String accountNumber;
	private String description;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Destinatario [id=" + id + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
				+ ", accountNumber=" + accountNumber + ", description=" + description + "]";
	}

	
	
	
}
