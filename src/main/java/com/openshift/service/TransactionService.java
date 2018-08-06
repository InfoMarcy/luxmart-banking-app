package com.openshift.service;

import java.security.Principal;
import java.util.List;

import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.model.cuentas.PrimaryTransaction;
import com.openshift.model.cuentas.SavingsTransaction;

public interface TransactionService {
	List<PrimaryTransaction> findPrimaryTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void savePrimaryDepositTransaction(PrimaryTransaction primaryTransaction , Principal principal);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction , Principal principal);
    
    void savePrimaryWithdrawTransaction(PrimaryTransaction primaryTransaction, Principal principal);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction, Principal principal);
    
    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, CuentaPrimaria primaryAccount, CuentaAhorros savingsAccount, Principal principal) throws Exception;
    
    List<Destinatario> findRecipientList(Principal principal);

    Destinatario saveRecipient(Destinatario recipient, Principal principal);

    Destinatario findRecipientByName(String recipientName, Principal principal);

    void deleteRecipientByName(String recipientName, Principal principal);
    
    void toSomeoneElseTransfer(Destinatario recipient, String accountType, String amount, CuentaPrimaria primaryAccount, CuentaAhorros savingsAccount, Principal principal);


}
