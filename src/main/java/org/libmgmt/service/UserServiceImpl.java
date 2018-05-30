package org.libmgmt.service;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.libmgmt.dao.UserDao;
import org.libmgmt.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserServiceImpl implements UserService {

	@Resource(name = "UserDaoImpl")
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

	public List<User> listUsers() {
		return userDao.listUsers();
	}

}
