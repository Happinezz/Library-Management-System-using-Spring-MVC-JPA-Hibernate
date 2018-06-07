package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Provides the services for all the user related operations
 * 
 * @author Karan
 *
 */
public interface UserService extends UserDetailsService {

	/**
	 * Adds new user
	 * 
	 * @param user
	 * @return
	 */
	User addUser(User user);

	/**
	 * Updates the user
	 * 
	 * @param user
	 * @return
	 */
	User updateUser(User user);

	/**
	 * Deletes the user
	 * 
	 * @param userId
	 * @return
	 */
	Integer deleteUser(Integer userId);

	/**
	 * Fetches the user details
	 * 
	 * @param id
	 * @return
	 */
	User getUser(Integer id);

	/**
	 * Returns the list of all users.
	 * 
	 * @return
	 */
	List<User> listUsers();

	/**
	 * Searches the user with given propertyName having given value and returns
	 * the list of matching users.
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	List<User> searchUser(String propertyName, String value);

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

}
