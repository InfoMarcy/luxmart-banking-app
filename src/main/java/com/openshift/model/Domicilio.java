package com.openshift.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;

public class Domicilio {
	
	@Id
	private String id;
	private String calle;
	private String noInterior;
	private String noExterior;
	private String callesDeReferencia;
	private String colonia;
	private String delegacion;
	private String estado;
	private String pais;
	private String postalCode;

	protected Domicilio() {
		this.id = UUID.randomUUID().toString();
	}

	public Domicilio(String calle, String noInterior, String noExterior, String callesDeReferencia, String colonia,
			String delegacion, String estado, String pais, String postalCode) {
		super();
		this.calle = calle;
		this.noInterior = noInterior;
		this.noExterior = noExterior;
		this.callesDeReferencia = callesDeReferencia;
		this.colonia = colonia;
		this.delegacion = delegacion;
		this.estado = estado;
		this.pais = pais;
		this.postalCode = postalCode;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNoInterior() {
		return noInterior;
	}

	public void setNoInterior(String noInterior) {
		this.noInterior = noInterior;
	}

	public String getNoExterior() {
		return noExterior;
	}

	public void setNoExterior(String noExterior) {
		this.noExterior = noExterior;
	}

	public String getCallesDeReferencia() {
		return callesDeReferencia;
	}

	public void setCallesDeReferencia(String callesDeReferencia) {
		this.callesDeReferencia = callesDeReferencia;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getDelegacion() {
		return delegacion;
	}

	public void setDelegacion(String delegacion) {
		this.delegacion = delegacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", calle=" + calle + ", noInterior=" + noInterior + ", noExterior=" + noExterior
				+ ", callesDeReferencia=" + callesDeReferencia + ", colonia=" + colonia + ", delegacion=" + delegacion
				+ ", estado=" + estado + ", pais=" + pais + ", postalCode=" + postalCode + "]";
	}

	



}
