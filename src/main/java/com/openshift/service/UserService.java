package com.openshift.service;

import java.security.Principal;

import com.openshift.model.User;
import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.model.cuentas.PrimaryTransaction;
import com.openshift.model.cuentas.SavingsTransaction;

public interface UserService {
	
	User findByUsername(String username);
	User findByEmail(String email);
	boolean checkUserExist(String username, String email);
	boolean checkUsernameExists(String username);
	boolean checkEmailExists(String email);

	User create(User user);
	
	void savePrimaryAccount(CuentaPrimaria primaryAccount, Principal principal);
	void saveSavingsAccount(CuentaAhorros savingsAccount, Principal principal);
	
	void savePrimaryTransaction(PrimaryTransaction primaryTransaction, Principal principal);
	void saveSavingsTransaction(SavingsTransaction savingsTransaction, Principal principal);
	
	// recipients
	Destinatario saveRecipient(Destinatario recipient, Principal principal);
	Destinatario findRecipientByName(String recipientName, Principal principal);
	void deleteRecipientByName(String recipientName, Principal principal);

	void AddTransferToPrimaryAccount(CuentaPrimaria primaryAccount, User recipientUser, Principal principal);
	void AddTransferToSavingsAccount(CuentaAhorros savingsAccount, User recipientUser, Principal principal);
	void save(User user);
	Iterable<User> findAll();
	void enableUser(String username);
	void disableUser(String username);
	
	

}
