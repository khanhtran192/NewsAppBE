package com.news.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.news.dto.UserDTO;
import com.news.service.UserService;
	
@CrossOrigin
@RestController
@RequestMapping("/api/news/user")
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("")
	public UserDTO getUser(@RequestParam String userName) {
		return userService.findByUserName(userName);
	}

	
}
