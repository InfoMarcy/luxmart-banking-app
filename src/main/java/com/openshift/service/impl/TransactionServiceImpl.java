package com.openshift.service.impl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openshift.model.User;
import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.model.cuentas.PrimaryTransaction;
import com.openshift.model.cuentas.SavingsTransaction;
import com.openshift.service.TransactionService;
import com.openshift.service.UserService;

@Service
public class TransactionServiceImpl implements TransactionService {


	@Autowired
	private UserService userService;

	
	
	

	public List<PrimaryTransaction> findPrimaryTransactionList(String username){
        User user = userService.findByUsername(username);
        List<PrimaryTransaction> primaryTransactionList = user.getCuentaPrimaria().getPrimaryTransactionList();

        return primaryTransactionList;
    }

    public List<SavingsTransaction> findSavingsTransactionList(String username) {
        User user = userService.findByUsername(username);
        List<SavingsTransaction> savingsTransactionList = user.getCuentaAhorros().getSavingsTransactionList();

        return savingsTransactionList;
    }

    public void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction, Principal principal) {
    	userService.savePrimaryTransaction(primaryTransaction, principal);
    }

    public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction, Principal principal) {
    	userService.saveSavingsTransaction(savingsTransaction, principal);
    }
    
    public void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction, Principal principal) {
    	userService.savePrimaryTransaction(primaryTransaction, principal);
    }

    public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction, Principal principal) {
    	userService.saveSavingsTransaction(savingsTransaction, principal);
    }
    
    // transfer balance between savings and primary account
    public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, CuentaPrimaria primaryAccount, CuentaAhorros savingsAccount, Principal principal) throws Exception {
       // check if the transfer is from primary to savings account
    	if (transferFrom.equalsIgnoreCase("Primary") && transferTo.equalsIgnoreCase("Savings")) {
        	
        	// Subtract the amount from primary account
            primaryAccount.setBalance(primaryAccount.getBalance().subtract(new BigDecimal(amount)));
            // add the amount to savings
            savingsAccount.setBalance(savingsAccount.getBalance().add(new BigDecimal(amount)));
            
            // save the primaryAccount changes
            userService.savePrimaryAccount(primaryAccount, principal);
            // save the savingsAccount changes
            userService.saveSavingsAccount(savingsAccount, principal);
          
           // get the date for the new transaction
            Date date = new Date();
            // set the values for the primary transactionList
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transferencia desde  "+transferFrom+" hacia "+transferTo, "Cuentas", "Realizada", Double.parseDouble(amount), primaryAccount.getBalance());
            // save the primaryTransaction and add it to the list
            userService.savePrimaryTransaction(primaryTransaction, principal);
            
            
            
            // set the values for the savings transactionList
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposito desde  "+transferTo+" hacia  "+transferFrom, "Cuentas", "Realizada", Double.parseDouble(amount), savingsAccount.getBalance());
            //save the savings transactions
            userService.saveSavingsTransaction(savingsTransaction, principal);
        } 
    	// check if the transfer is from savings  to primary account
    	else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Primary")) {
    		//   add the transfer amount to primary
            primaryAccount.setBalance(primaryAccount.getBalance().add(new BigDecimal(amount)));
       //   Subtract the transfer amount to savings
            savingsAccount.setBalance(savingsAccount.getBalance().subtract(new BigDecimal(amount)));
         // save the primaryAccount changes
            userService.savePrimaryAccount(primaryAccount, principal);
         // save the savingsAccount changes
            userService.saveSavingsAccount(savingsAccount, principal);

            // get the date for the new transaction
            Date date = new Date();
            // set the values for the savings transactionList
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transferencia desde  "+transferFrom+" hacia "+transferTo, "Transferencias", "Realizada", Double.parseDouble(amount), savingsAccount.getBalance());
            //save the savings transactions
            userService.saveSavingsTransaction(savingsTransaction, principal);
            
            // set the values for the savings transactionList
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Deposito a  "+transferTo+" desde "+transferFrom, "Cuenta", "Realizada", Double.parseDouble(amount), primaryAccount.getBalance());
            //save the savings transactions
            userService.savePrimaryTransaction(primaryTransaction, principal);
        } else { // otherwise throw an error
            throw new Exception("Invalid Transfer");
        }
    }
    
    // find the list of recipients
    public List<Destinatario> findRecipientList(Principal principal) {
    	// get the current user
    	User user = userService.findByEmail(principal.getName());   	      
        
    	// get the list of recipients
        List<Destinatario> recipientList = user.getDestinatarios();

        
        // return the list of recipients
        return recipientList;
    }

    public Destinatario saveRecipient(Destinatario recipient, Principal principal) {
        return userService.saveRecipient(recipient, principal);
    }

    public Destinatario findRecipientByName(String recipientName, Principal principal) {
        return userService.findRecipientByName(recipientName, principal);
    }

    public void deleteRecipientByName(String recipientName, Principal principal) {
    	userService.deleteRecipientByName(recipientName, principal);
    }
    
    
    // transfer balance from one person account to another
    public void toSomeoneElseTransfer(Destinatario recipient, String accountType, String amount, CuentaPrimaria primaryAccount, CuentaAhorros savingsAccount, Principal principal) {
       // check if it is the primary account
    	if (accountType.equalsIgnoreCase("Cuenta Primaria")) {
    		// Subtract the balance from the primary account
            primaryAccount.setBalance(primaryAccount.getBalance().subtract(new BigDecimal(amount))); 
            // save the transaction
            userService.savePrimaryAccount(primaryAccount, principal);
           
            // get the date for the transaction
            Date date = new Date();

            // add the values for the primary transaction record
            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, "Transferencia al destinatario  "+recipient.getNombre(), "Transferencia", "Realizada", Double.parseDouble(amount), primaryAccount.getBalance());
            // save the primary transaction record
            userService.savePrimaryTransaction(primaryTransaction, principal);
        } 
    	// check if it is the savingsAccount
    	else if (accountType.equalsIgnoreCase("Ahorros")) {
    		// Subtract the balance from the savingsAccount
            savingsAccount.setBalance(savingsAccount.getBalance().subtract(new BigDecimal(amount)));
            // save the transaction
             userService.saveSavingsAccount(savingsAccount, principal);
          // get the date for the transaction
            Date date = new Date();
            // add the values for the savings transaction record
            SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Transferencia a otras cuentas al destinatario "+ recipient.getNombre(), "Transferencia", "Realizada", Double.parseDouble(amount), savingsAccount.getBalance());
            // save the savings transaction record 
            userService.saveSavingsTransaction(savingsTransaction, principal);
        }
    	
    	addBalanceToTransferredAccount(recipient, amount, principal);
    }

	public void addBalanceToTransferredAccount(Destinatario recipient,String amount, Principal principal) {
		
		System.out.println("1 =>" + recipient);
		
		// get the user to transferto
//		 User recipientUser = userService.findByEmail(recipient.getEmail().toString());
		 
		User recipientUser = userService.findByEmail(principal.getName());
				
		 // get the user that is making the transfer          
		 User currentUser = userService.findByEmail(principal.getName());
		 // check if it is the primaryAccount	
		 System.out.println("2 =>" +currentUser);
		 System.out.println("3 =>" +recipientUser);
		 
		 
		 if(recipientUser.getCuentaPrimaria().getNumeroDeCuenta() == Integer.parseInt(recipient.getAccountNumber()) ) {
			// Add the balance to the primary account
			 recipientUser.getCuentaPrimaria().setBalance(recipientUser.getCuentaPrimaria().getBalance().add(new BigDecimal(amount))); 
	            // save the transaction
	            userService.AddTransferToPrimaryAccount(recipientUser.getCuentaPrimaria(), recipientUser , principal);
	            
	            
	            // get the date for the transaction
	            Date date = new Date();

	            // add the values for the primary transaction record
	            PrimaryTransaction primaryTransaction = new PrimaryTransaction(date, 
	            		"Transferencia desde "+ currentUser.getNombre() +" " + currentUser.getApellidoPaterno(), 
	            		"Transferencia", "Realizada", Double.parseDouble(amount), recipientUser.getCuentaPrimaria().getBalance());
	          
	            // save the primary transaction record 
	            recipientUser.getCuentaPrimaria().getPrimaryTransactionList().add(primaryTransaction);
	            
	    		// save the update fields
	            userService.save(recipientUser);
	          
	            
		 } 
		// check if it is the primaryAccount	
		 else if(recipientUser.getCuentaAhorros().getNumeroDeCuenta() == Integer.parseInt(recipient.getAccountNumber()) ) {
			// Add the balance to the primary account
			 recipientUser.getCuentaAhorros().setBalance(recipientUser.getCuentaAhorros().getBalance().add(new BigDecimal(amount))); 
	            // save the transaction
	            userService.AddTransferToSavingsAccount(recipientUser.getCuentaAhorros(), recipientUser, principal);
	            
	            
	            // get the date for the transaction
	            Date date = new Date();

	            // add the values for the primary transaction record
	            SavingsTransaction savingsTransaction = new SavingsTransaction(date, 
	            		"Transferencia desde "+ currentUser.getNombre() +" " + currentUser.getApellidoPaterno(), 
	            		"Transferencia", "Realizada", Double.parseDouble(amount), recipientUser.getCuentaAhorros().getBalance());
	          
	            // save the primary transaction record 
	            recipientUser.getCuentaAhorros().getSavingsTransactionList().add(savingsTransaction);
	            
	    		// save the update fields
	            userService.save(recipientUser);
		 }
		 else {
			System.out.println("The specify account does not exist Role Transaction Back");
		 }
	}




}
