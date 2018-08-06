package com.openshift.service.impl;

import org.springframework.stereotype.Service;

import com.openshift.model.Role;
import com.openshift.model.security.CurrentUser;


@Service
public class CurrentUserService {

	public boolean canAccessUser(CurrentUser currentUser, String userId) {
//       log.debug("Checking if user={} has access to user={}", currentUser, userId);
        return currentUser != null
                && (currentUser.getRole() == Role.ROLE_ADMIN || currentUser.getId().equals(userId));
    }
}
