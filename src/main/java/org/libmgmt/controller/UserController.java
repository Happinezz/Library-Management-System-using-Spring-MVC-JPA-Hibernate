package org.libmgmt.controller;

import java.util.List;

import javax.annotation.Resource;

import org.libmgmt.dao.UserDao;
import org.libmgmt.model.User;
import org.libmgmt.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource(name = "UserServiceImpl")
	UserService userService;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView showForm() {
		return new ModelAndView("addUser", "user", new User());
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addUser(@ModelAttribute User user) {
		userService.addUser(user);
		return new ModelAndView("redirect:/user/get/" + user.getUserId(), "user", user);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public ModelAndView deleteUser(@ModelAttribute User user) {
		userService.deleteUser(user.getUserId());
		return new ModelAndView("displayUser", "user", new User());
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateUser(@ModelAttribute User user) {
		user = userService.updateUser(user);
		return new ModelAndView("redirect:/user/get/" + user.getUserId(), "user", user);
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ModelAndView getUser(@PathVariable("id") Integer id) {
		User user = userService.getUser(id);
		return new ModelAndView("displayUser", "user", user);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listUser() {
		List<User> users = userService.listUsers();
		return new ModelAndView("listUser", "users", users);
	}
}