package org.libmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.User;
import org.libmgmt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return new ModelAndView("UserDetails", "user", user);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("NewUser", "user", new User());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user) {
		user = userService.updateUser(user);
		return redirectToUserManagement();
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return redirectToUserManagement();
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return redirectToUserManagement();
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<User> users = userService.searchUser(propertyName, propertyValue);
		return redirectToUserList(users);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser() {
		List<User> users = userService.listUsers();
		return redirectToUserList(users);
	}

	private ModelAndView redirectToUserManagement() {
		return new ModelAndView("redirect:/UserManagement.jsp");
	}

	private ModelAndView redirectToUserList(List<User> users) {
		return new ModelAndView("UserList", "users", users);
	}
}