package org.libmgmt.dao;

import java.util.List;

import org.libmgmt.model.User;

public interface UserDao {

	User addUser(User user);

	User updateUser(User user);

	Integer deleteUser(Integer userId);

	User getUser(Integer id);

	List<User> listUsers();

}
