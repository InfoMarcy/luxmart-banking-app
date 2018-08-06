package com.openshift.service.impl;

import java.math.RoundingMode;
import java.security.Principal;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.openshift.model.User;
import com.openshift.model.actividades.Destinatario;
import com.openshift.model.cuentas.CuentaAhorros;
import com.openshift.model.cuentas.CuentaPrimaria;
import com.openshift.model.cuentas.PrimaryTransaction;
import com.openshift.model.cuentas.SavingsTransaction;
import com.openshift.model.repository.UserRepository;
import com.openshift.service.AccountService;
import com.openshift.service.UserService;



@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	// connect to the user repository
	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Autowired
	AccountService accountService;

	public User create(User user) {
		// check if the user exist
		User localUser = userRepository.findByUsername(user.getUsername());
		// if user not exist
		if (localUser != null) {// if user does not exist
			LOG.info("User with username {} already exist. Nothing will be done. ", user.getUsername());
		} else {// if user exist
			String encryptedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encryptedPassword);

			// set the accounts for the new user
			user.setCuentaPrimaria(accountService.createPrimaryAccount());
			user.setCuentaAhorros(accountService.createSavingsAccount());

			// save the new user
			localUser = userRepository.save(user);
		}

		// return the user
		return localUser;
	}

	// find user by email
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// find user by username
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// check if the user already exist
	public boolean checkUserExist(String username, String email) {
		if (checkUsernameExists(username) || checkEmailExists(email)) {
			return true;
		} else {
			return false;
		}

	}

	// check if the username exist
	public boolean checkUsernameExists(String username) {
		if (null != findByUsername(username)) {
			return true;
		} else {
			return false;
		}
	}

	// check if the email exist
	public boolean checkEmailExists(String email) {
		if (null != findByEmail(email)) {
			return true;
		} else {
			return false;
		}
	}

	// update the account balance -- deposit to the account
	@Override
	public void savePrimaryAccount(CuentaPrimaria primaryAccount, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());

		// modify
		user.getCuentaPrimaria()
				.setBalance(primaryAccount.getBalance().setScale(2, RoundingMode.HALF_UP));
		// update the account
		userRepository.save(user);

	}

	// update the account balance -- deposit to the account
	@Override
	public void saveSavingsAccount(CuentaAhorros savingsAccount, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());

		// modify
		user.getCuentaAhorros()
				.setBalance(savingsAccount.getBalance().setScale(2, RoundingMode.HALF_UP));
		// update the account
		userRepository.save(user);

	}

	// add the new transaction to transactions list
	@Override
	public void savePrimaryTransaction(PrimaryTransaction primaryTransaction, Principal principal) {

		// get the current user
		User user = this.findByEmail(principal.getName());

		// add an array to a subdocument
		user.getCuentaPrimaria().getPrimaryTransactionList().add(primaryTransaction);

		// save the update fields
		userRepository.save(user);

	}

	// add the new transaction to transactions list
	@Override
	public void saveSavingsTransaction(SavingsTransaction savingsTransaction, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());
		// add an array to a subDocument
		user.getCuentaAhorros().getSavingsTransactionList().add(savingsTransaction);
		// save the update fields
		userRepository.save(user);

	}

	// save a new recipient
	@Override
	public Destinatario saveRecipient(Destinatario recipient, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());

		// iterate over the recipients
		for (Destinatario item : user.getDestinatarios()) {

			// check if the recipient is the one we are looking for
			if (item.getId().toString().equals(recipient.getId().toString())) {

				// remove the recipient from the class
				user.getDestinatarios().remove(item);
				// add the recipient to the classs
				user.getDestinatarios().add(recipient);
				// save the recipient
				userRepository.save(user);
				// return the recipient
				return recipient;
			}

		}

		// add a recipient to the user
		user.getDestinatarios().add(recipient);
		// save the recipient
		userRepository.save(user);
		return recipient;
	}

	// get a recipient by name
	@Override
	public Destinatario findRecipientByName(String recipientName, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());

		Destinatario foundRecipient = new Destinatario();

		// iterate over the recipients
		for (Destinatario recipient : user.getDestinatarios()) {

			// check if the recipient is the one we are looking for
			if (recipient.getNombre().equals(recipientName)) {
				// return the selected recipient
				return foundRecipient = recipient;
			}

		}

		// no recipients found
		return foundRecipient;
	}

	// delete a recipient by name
	@Override
	public void deleteRecipientByName(String recipientName, Principal principal) {
		// get the current user
		User user = this.findByEmail(principal.getName());

		Optional<Destinatario> deleteRecipient = user.getDestinatarios().stream() // convert list to stream
				.filter(recipient -> recipientName.equals(recipient.getNombre())) // filter the recipient by name
				.findFirst(); // get the first match

		// check if the recipient is not null
		if (deleteRecipient.get() != null) {
			// remove the recipient from the class
			user.getDestinatarios().remove(deleteRecipient.get());
			// update and save the changes
			userRepository.save(user);
		}

	}

	@Override
	public void AddTransferToPrimaryAccount(CuentaPrimaria primaryAccount, User recipientUser, Principal principal) {
		// modify
		recipientUser.getCuentaPrimaria()
				.setBalance(primaryAccount.getBalance().setScale(2, RoundingMode.HALF_UP));
		// update the account
		userRepository.save(recipientUser);

	}

	@Override
	public void AddTransferToSavingsAccount(CuentaAhorros savingsAccount, User recipientUser, Principal principal) {
		// modify
		recipientUser.getCuentaAhorros()
				.setBalance(savingsAccount.getBalance().setScale(2, RoundingMode.HALF_UP));
		// update the account
		userRepository.save(recipientUser);

	}

	// save the user
	@Override
	public void save(User user) {
		userRepository.save(user);

	}

	// find all users
	@Override
	public Iterable<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public void enableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(true);
		userRepository.save(user);
	}

	public void disableUser(String username) {
		User user = findByUsername(username);
		user.setEnabled(false);
		// System.out.println(user.isEnabled());
		userRepository.save(user);
		System.out.println(username + " is disabled.");
	}

}
