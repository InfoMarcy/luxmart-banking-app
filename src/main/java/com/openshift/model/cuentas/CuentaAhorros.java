package com.openshift.model.cuentas;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CuentaAhorros {

	private int numeroDeCuenta;
	private BigDecimal balance;
	
	private List<SavingsTransaction> SavingsTransactionList;


	public CuentaAhorros() {
		super();
		this.SavingsTransactionList = new ArrayList<>();
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


	public List<SavingsTransaction> getSavingsTransactionList() {
		return SavingsTransactionList;
	}


	public void setSavingsTransactionList(List<SavingsTransaction> savingsTransactionList) {
		SavingsTransactionList = savingsTransactionList;
	}


	
	
	
}
