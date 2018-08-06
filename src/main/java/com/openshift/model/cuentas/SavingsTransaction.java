package com.openshift.model.cuentas;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

public class SavingsTransaction {
	
	@Id
	private String id;
	private Date fecha;
	private String descripcion;
	private String tipo;
	private String status;
	private Double cantidad;
	private BigDecimal balanceDisponible;
	
	
	public SavingsTransaction() {
		super();
		this.id = UUID.randomUUID().toString();
	}
	
	

	public SavingsTransaction(Date fecha, String descripcion, String tipo, String status, Double cantidad,
			BigDecimal balanceDisponible) {
		super();
		this.fecha = fecha;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.status = status;
		this.cantidad = cantidad;
		this.balanceDisponible = balanceDisponible;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Date getFecha() {
		return fecha;
	}



	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public String getTipo() {
		return tipo;
	}



	public void setTipo(String tipo) {
		this.tipo = tipo;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Double getCantidad() {
		return cantidad;
	}



	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}



	public BigDecimal getBalanceDisponible() {
		return balanceDisponible;
	}



	public void setBalanceDisponible(BigDecimal balanceDisponible) {
		this.balanceDisponible = balanceDisponible;
	}

	

	
}
