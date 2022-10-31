package com.news.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	long idNews;
	String content;
	MultipartFile image;


}
