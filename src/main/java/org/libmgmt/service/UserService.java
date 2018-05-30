package org.libmgmt.service;

import java.util.List;

import org.libmgmt.model.User;

public interface UserService {

	User addUser(User user);

	User updateUser(User user);

	Integer deleteUser(Integer userId);

	User getUser(Integer id);

	List<User> listUsers();

}
