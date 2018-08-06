package com.openshift.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openshift.model.User;
import com.openshift.model.repository.UserRepository;
import com.openshift.model.security.CurrentUser;


@Service
public class UserSecurityService implements UserDetailsService {


	/** The application logger */
	private static final Logger LOG = LoggerFactory.getLogger(UserSecurityService.class);

	@Autowired
	private UserRepository userRepository;


	 @Override
	    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
 
		 User user = userRepository.findByUsername(username);
			if (null == user) {
				LOG.warn("Username {} not found", username);
				throw new UsernameNotFoundException("Username " + username + " not found");
			}
	        
	        return new CurrentUser(user);
	    }



}
