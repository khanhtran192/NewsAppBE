package com.news.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.ContentDTO;
import com.news.entity.Content;
import com.news.repos.ContentRepos;
import com.news.repos.NewsRepos;
import com.news.service.ContentService;
@Service
public class ContentServiceImpl implements ContentService {
	@Autowired
	ContentRepos desRepos;
	
	@Autowired
	NewsRepos newsRepos;

	@Override
	public void createContent(ContentDTO desMore) {

	}
	
}
