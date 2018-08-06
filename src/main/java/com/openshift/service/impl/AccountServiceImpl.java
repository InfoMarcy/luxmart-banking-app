package com.openshift.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openshift.model.User;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.model.cuentas.PrimaryTransaction;
import com.openshift.model.cuentas.SavingsTransaction;
import com.openshift.service.AccountService;
import com.openshift.service.TransactionService;
import com.openshift.service.UserService;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
	// base number for the accountNumber
	private static int nextAccountNumber = 11223145;

	@Autowired
	UserService userService;
	
	@Autowired
	TransactionService transactionService;
	
	// create a primary account for the new user
	@Override
	public CuentaPrimaria createPrimaryAccount() {
		// initialize a new primaryAccount
		CuentaPrimaria primaryAccount = new CuentaPrimaria();
		// set the balance of the newly create account to 0
        primaryAccount.setBalance(new BigDecimal(0.0));
        //assign an account number to the new account
        primaryAccount.setNumeroDeCuenta(accountGen());

        
        // return the model object
        return primaryAccount;
	}

	// create a savings account for the new user
	@Override
	public CuentaAhorros createSavingsAccount() {
		// initialize a new savingsAccount
		CuentaAhorros savingsAccount = new CuentaAhorros();
		// set the balance of the newly create account to 0
        savingsAccount.setBalance(new BigDecimal(0.0));
      //assign an account number to the new account
        savingsAccount.setNumeroDeCuenta(accountGen());
        // return the model object
        return savingsAccount;
	}

	// increase the accountNumber
	  private int accountGen() {
	        return ++nextAccountNumber;
	    }
	  
	  
	  // do a deposit
	  public void deposit(String accountType, double amount, Principal principal) {
		 
		  // get the user for the current account
	        User user = userService.findByEmail(principal.getName());
	     
	        // check if the selected account is the primary account
	        if (accountType.equalsIgnoreCase("Cuenta Primaria")) {
	        	CuentaPrimaria primaryAccount = user.getCuentaPrimaria();
	            primaryAccount.setBalance(primaryAccount.getBalance().add(new BigDecimal(amount)));
	            userService.savePrimaryAccount(primaryAccount, principal);

	            
	            // get the current date to assign it to the transaction record
	            Date date = new Date();

	            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposito a la cuenta primaria", "Cuenta", "Realizada", amount, primaryAccount.getBalance());
	            transactionService.savePrimaryDepositTransaction(primaryTransaction, principal);
	            
	        } 
	     // check if the selected account is the savings account
	        else if (accountType.equalsIgnoreCase("Ahorros")) {
	        	CuentaAhorros savingsAccount = user.getCuentaAhorros();
	            // sum the values  for the account balance
	            savingsAccount.setBalance(savingsAccount.getBalance().add(new BigDecimal(amount)));
	            // save the changes to the account
	            userService.saveSavingsAccount(savingsAccount, principal);
//
	            Date date = new Date();
	            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposito a la cuenta de ahorros", "Cuenta", "Realizada", amount, savingsAccount.getBalance());
	            transactionService.saveSavingsDepositTransaction(savingsTransaction, principal);
	        }
	    }

	  public void withdraw(String accountType, double amount, Principal principal) {
		  // get the current user
	        User user = userService.findByEmail(principal.getName());

	        // check if the selected account is the primary account
	        if (accountType.equalsIgnoreCase("Cuenta Primaria")) {
	        	CuentaPrimaria primaryAccount = user.getCuentaPrimaria();
	            // rest the values  for the account balance
	            primaryAccount.setBalance(primaryAccount.getBalance().subtract(new BigDecimal(amount)));
	            // save the changes to the account
	            userService.savePrimaryAccount(primaryAccount, principal);

	            Date date = new Date();

	            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Retiro de la cuenta primaria", "Cuenta", "Realizada", amount, primaryAccount.getBalance());
	            transactionService.savePrimaryWithdrawTransaction(primaryTransaction, principal);
	        } else if (accountType.equalsIgnoreCase("Ahorros")) {
	        	CuentaAhorros savingsAccount = user.getCuentaAhorros();
	            savingsAccount.setBalance(savingsAccount.getBalance().subtract(new BigDecimal(amount)));
	            // save the changes to the account
	            userService.saveSavingsAccount(savingsAccount, principal);

	            Date date = new Date();
	            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Retiro de la cuenta de ahorros", "Cuenta", "Realizada", amount, savingsAccount.getBalance());
	            transactionService.saveSavingsWithdrawTransaction(savingsTransaction, principal);
	        }
	    }
}
