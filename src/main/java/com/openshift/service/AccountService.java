package com.openshift.service;

import java.security.Principal;

import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;


public interface AccountService {
	CuentaPrimaria  createPrimaryAccount();
	CuentaAhorros createSavingsAccount();
	
	void deposit(String accountType, double amount, Principal principal);
	void withdraw(String accountType, double amount, Principal principal);

}
