package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.User;

public interface UserService {

	User addUser(User book);

	User updateUser(User book);

	Integer deleteUser(Integer bookId);

	User getUser(Integer id);

	List<User> listUsers();

	List<User> searchUser(String propertyName, String value);

}
