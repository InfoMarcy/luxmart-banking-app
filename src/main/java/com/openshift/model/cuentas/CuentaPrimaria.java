package com.openshift.model.cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CuentaPrimaria {
	

	private int numeroDeCuenta;
	private BigDecimal balance;
	
	@JsonIgnore
	private List<PrimaryTransaction> PrimaryTransactionList;

	public CuentaPrimaria() {
		super();
		this.PrimaryTransactionList = new ArrayList<>();
		}

	public int getNumeroDeCuenta() {
		return numeroDeCuenta;
	}

	public void setNumeroDeCuenta(int numeroDeCuenta) {
		this.numeroDeCuenta = numeroDeCuenta;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public List<PrimaryTransaction> getPrimaryTransactionList() {
		return PrimaryTransactionList;
	}

	public void setPrimaryTransactionList(List<PrimaryTransaction> primaryTransactionList) {
		PrimaryTransactionList = primaryTransactionList;
	}
	
	

}
