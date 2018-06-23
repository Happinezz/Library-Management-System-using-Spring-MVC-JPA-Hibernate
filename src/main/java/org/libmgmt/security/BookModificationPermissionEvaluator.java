package org.libmgmt.security;

import java.io.Serializable;

import org.libmgmt.model.Book;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;

public class BookModificationPermissionEvaluator implements PermissionEvaluator {

	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		Book book = (Book) targetDomainObject;
		return hasPrivilege(authentication, book.getId());
	}

	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return hasPrivilege(authentication, targetId);
	}

	private boolean hasPrivilege(Authentication authentication, Serializable targetId) {
		authentication.getPrincipal();
		return false;
	}

}