package com.openshift.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.openshift.model.actividades.Cita;
import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;

@Document
public class User {

	@Id
	private String id;
	private String username;
	@JsonIgnore
	private String password;
	private String nombre;
	private String segundoNombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	@Indexed(direction = IndexDirection.ASCENDING)
	private String email;
	private String telefono;
	private String sexo;
	private String ocupacion;
	private String estadoCivil;
	private String rfc;
	private String nacionalidad;
	private String curp;
	private String fechaNaci;
	private String lugarNaci;
	private String imageUrl;

	private List<Domicilio> domicilio;

	private Role role;

	private DatosTelefonicos datosTelefonicos;

	// Banking Data
	private CuentaPrimaria cuentaPrimaria;
	private CuentaAhorros cuentaAhorros;

	private List<Destinatario> destinatarios;
	private List<Cita> citas;

	// protected constructor
	public User() {
		this.domicilio = new ArrayList<>();
		this.role = Role.ROLE_USER;
		this.destinatarios = new ArrayList<>();
		this.citas = new ArrayList<>();
	}

	public User(String username, String password, String nombre, String segundoNombre, String apellidoPaterno,
			String apellidoMaterno, String email, String telefono, DatosTelefonicos datosTelefonicos, int edad,
			String sexo, String ocupacion, String estadoCivil, String rfc, String nacionalidad, String curp,
			String fechaNaci, String lugarNaci, String imageUrl, List<Domicilio> domicilio) {
		super();
		this.username = username;
		this.password = password;
		this.nombre = nombre;
		this.segundoNombre = segundoNombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.email = email;
		this.telefono = telefono;
		this.datosTelefonicos = datosTelefonicos;
		this.sexo = sexo;
		this.ocupacion = ocupacion;
		this.estadoCivil = estadoCivil;
		this.rfc = rfc;
		this.nacionalidad = nacionalidad;
		this.curp = curp;
		this.fechaNaci = fechaNaci;
		this.lugarNaci = lugarNaci;
		this.imageUrl = imageUrl;
		this.domicilio = domicilio;

	}

	@JsonIgnore
	private boolean enabled = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
	}

	public List<Domicilio> getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(List<Domicilio> domicilio) {
		this.domicilio = domicilio;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFechaNaci() {
		return fechaNaci;
	}

	public void setFechaNaci(String fechaNaci) {
		this.fechaNaci = fechaNaci;
	}

	public String getLugarNaci() {
		return lugarNaci;
	}

	public void setLugarNaci(String lugarNaci) {
		this.lugarNaci = lugarNaci;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public DatosTelefonicos getDatosTelefonicos() {
		return datosTelefonicos;
	}

	public void setDatosTelefonicos(DatosTelefonicos datosTelefonicos) {
		this.datosTelefonicos = datosTelefonicos;
	}

	public CuentaPrimaria getCuentaPrimaria() {
		return cuentaPrimaria;
	}

	public void setCuentaPrimaria(CuentaPrimaria cuentaPrimaria) {
		this.cuentaPrimaria = cuentaPrimaria;
	}

	public CuentaAhorros getCuentaAhorros() {
		return cuentaAhorros;
	}

	public void setCuentaAhorros(CuentaAhorros cuentaAhorros) {
		this.cuentaAhorros = cuentaAhorros;
	}

	public List<Destinatario> getDestinatarios() {
		return destinatarios;
	}

	public void setDestinatarios(List<Destinatario> destinatarios) {
		this.destinatarios = destinatarios;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nombre=" + nombre
				+ ", segundoNombre=" + segundoNombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
				+ apellidoMaterno + ", email=" + email + ", telefono=" + telefono + ", sexo=" + sexo
				+ ", ocupacion=" + ocupacion + ", estadoCivil=" + estadoCivil + ", rfc=" + rfc + ", nacionalidad="
				+ nacionalidad + ", curp=" + curp + ", fechaNaci=" + fechaNaci + ", lugarNaci=" + lugarNaci
				+ ", imageUrl=" + imageUrl + ", domicilio=" + domicilio + ", role=" + role + ", datosTelefonicos="
				+ datosTelefonicos + ", cuentaPrimaria=" + cuentaPrimaria + ", cuentaAhorros=" + cuentaAhorros
				+ ", destinatarios=" + destinatarios + ", citas=" + citas + ", enabled=" + enabled + "]";
	}

}
