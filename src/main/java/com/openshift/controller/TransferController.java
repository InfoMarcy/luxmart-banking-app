package com.openshift.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.openshift.model.User;
import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.service.TransactionService;
import com.openshift.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    // get the transfer page
    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.GET)
    public String betweenAccounts(Model model) {
    	// add the model to the transfer page
        model.addAttribute("transferFrom", "");
        model.addAttribute("transferTo", "");
        model.addAttribute("amount", "");

        // return the transfer view
        return "betweenAccounts";
    }

    
    // post data to transfer between accounts
    @RequestMapping(value = "/betweenAccounts", method = RequestMethod.POST)
    public String betweenAccountsPost(
            @ModelAttribute("transferFrom") String transferFrom,
            @ModelAttribute("transferTo") String transferTo,
            @ModelAttribute("amount") String amount,
            Principal principal
    ) throws Exception {
    	// get the current logged in user
        User user = userService.findByEmail(principal.getName());
        //get the primary account for the user
        CuentaPrimaria primaryAccount = user.getCuentaPrimaria();
        //get the savings account for the user
        CuentaAhorros savingsAccount = user.getCuentaAhorros();
        // transfer the balance from one account ot another
        transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount, primaryAccount, savingsAccount, principal);

        return "redirect:/userFront";
    }
    
    // find whatever recipient we have
    @RequestMapping(value = "/recipient", method = RequestMethod.GET)
    public String recipient(Model model, Principal principal) {
    	// get the list of recipients
        List<Destinatario> recipientList = transactionService.findRecipientList(principal);

        // create a new recipient instance
        Destinatario recipient = new Destinatario();

        // Initialize the recipient model
        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        // return the recipient view page
        return "recipient";
    }

    // post and save the recipient
    @RequestMapping(value = "/recipient/save", method = RequestMethod.POST)
    public String recipientPost(@ModelAttribute("recipient") Destinatario recipient, Principal principal) {
           
        // save the recipient
        transactionService.saveRecipient(recipient, principal);

        // redirect to the recipient page
        return "redirect:/transfer/recipient";
    }

    // edit the recipients 
    @RequestMapping(value = "/recipient/edit", method = RequestMethod.GET)
    public String recipientEdit(@RequestParam(value = "recipientName") String recipientName, Model model, Principal principal){

    	// get the recipient by name
    	Destinatario recipient = transactionService.findRecipientByName(recipientName, principal);
        // Retrieve the list of recipients
        List<Destinatario> recipientList = transactionService.findRecipientList(principal);

        // add the recipients to the model
        model.addAttribute("recipientList", recipientList);
        model.addAttribute("recipient", recipient);

        // return the view
        return "recipient";
    }

    
    // delete a recipient 
    @RequestMapping(value = "/recipient/delete", method = RequestMethod.GET)
    public String recipientDelete(@RequestParam(value = "recipientName") String recipientName, Principal principal){

    	// Retrieve the recipient that we want to delete
        transactionService.deleteRecipientByName(recipientName, principal);

        // redirect to the recipient page
        return "redirect:/transfer/recipient";
    }

    
    // transfer balance from one person to another
    @RequestMapping(value = "/toSomeoneElse",method = RequestMethod.GET)
    public String toSomeoneElse(Model model, Principal principal) {
    	// get the recipient list
        List<Destinatario> recipientList = transactionService.findRecipientList(principal);

        // add the recipients to the model
        model.addAttribute("recipientList", recipientList);
        model.addAttribute("accountType", "");

        // return the view
        return "toSomeoneElse";
    }

    // process the transfer to another person
    @RequestMapping(value = "/toSomeoneElse",method = RequestMethod.POST)
    public String toSomeoneElsePost(@ModelAttribute("recipientName") String recipientName, @ModelAttribute("accountType") String accountType, @ModelAttribute("amount") String amount, Principal principal) {
        // get the logged in user
    	User user = userService.findByEmail(principal.getName());
    	// find a recipient by name
    	Destinatario recipient = transactionService.findRecipientByName(recipientName, principal);
        //transfer balance from accounts
        transactionService.toSomeoneElseTransfer(recipient, accountType, amount, user.getCuentaPrimaria(), user.getCuentaAhorros(), principal);

       
        
        //redirect to userFront
        return "redirect:/userFront";
    }
}