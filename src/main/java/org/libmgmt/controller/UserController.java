package org.libmgmt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.libmgmt.model.User;
import org.libmgmt.service.UserService;
import org.libmgmt.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	private static final String EMAIL = "email";
	@Autowired
	UserService userService;

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return new ModelAndView("UserDetails", "user", user);
	}

	// @PreAuthorize(Constants.HAS_ROLE_ROLE_USER)
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody User getUserByEmail(HttpServletRequest req, HttpServletResponse resp) {
		String emailId = "admin";
		// req.getUserPrincipal().getName()
		List<User> users = userService.searchUser(EMAIL, emailId);
		return users.get(0);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("NewUser", "user", new User());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user, HttpServletRequest req, HttpServletResponse resp) {
		user = userService.updateUser(user);
		if (!req.isUserInRole("ROLE_ADMIN")) {
			return new ModelAndView("user_home");
		}
		return redirectToUserManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return redirectToUserManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteUser(@PathVariable("id") Integer id) {
		userService.deleteUser(id);
		return redirectToUserManagement();
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView searchUser(HttpServletRequest req, HttpServletResponse resp) {
		String propertyName = req.getParameter("property");
		String propertyValue = req.getParameter("value");
		List<User> users = userService.searchUser(propertyName, propertyValue);
		return redirectToUserList(users);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser() {
		List<User> users = userService.listUsers();
		return redirectToUserList(users);
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	private ModelAndView redirectToUserManagement() {
		return new ModelAndView("redirect:/UserManagement.jsp");
	}

	@PreAuthorize(Constants.HAS_ROLE_ROLE_ADMIN)
	private ModelAndView redirectToUserList(List<User> users) {
		return new ModelAndView("UserList", "users", users);
	}
}