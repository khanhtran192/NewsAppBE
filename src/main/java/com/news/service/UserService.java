package com.news.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.news.dto.UserDTO;
import com.news.dto.create.UserCreateDTO;
import com.news.entity.User;

public interface UserService {

	UserDTO findByUserName(String UserName);
	
	User getUser(String userName,String password);

	List<User> findAll();

	String createUser(UserCreateDTO dto);
	void updateUser(String password,String userName);
	
	

}
