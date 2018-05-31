package org.libmgmt.service;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.UserDao;
import org.libmgmt.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Can contain business logic and @Transactional because there can be multiple
 * objects to be updated.
 * 
 * @author Karan
 *
 */

@Service("UserService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "UserDao")
	UserDao userDao;

	public User addUser(User user) {
		return userDao.addUser(user);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public Integer deleteUser(Integer userId) {
		return userDao.deleteUser(userId);
	}

	public User getUser(Integer id) {
		return userDao.getUser(id);
	}

	public List<User> searchUser(String propertyName, String value) {
		return userDao.searchUser(propertyName, value);
	}

	public List<User> listUsers() {
		return userDao.listUsers();
	}

}
