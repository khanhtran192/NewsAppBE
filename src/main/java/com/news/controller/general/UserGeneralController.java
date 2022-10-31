package com.news.controller.general;

import javax.servlet.http.HttpServletRequest;

import com.news.dto.LoginDTO;
import com.news.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.news.config.JwtProvider;
import com.news.dto.JwtResponse;
import com.news.dto.create.UserCreateDTO;
import com.news.service.UserService;

@RestController
@RequestMapping("/api/news/general")
public class UserGeneralController {
	@Autowired
	UserService userService;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JwtProvider jwt;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(LoginDTO loginDTO) {
		try{
			Authentication authentication =manager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			UserDetails user=(UserDetails) authentication.getPrincipal();
			UserDTO userInfo = userService.findByUserName(user.getUsername());

			return new ResponseEntity<>(new JwtResponse(jwt.createToken(user),
					userInfo.getUserName(),userInfo.getFullName(), userInfo.getEmail()), HttpStatus.OK);
		}catch (Exception ex){

		}
		return null;
	}
	
	@PostMapping(value="/register")
	public String register(@RequestBody UserCreateDTO dto) {
		return userService.createUser(dto);
	}
	
	
}
