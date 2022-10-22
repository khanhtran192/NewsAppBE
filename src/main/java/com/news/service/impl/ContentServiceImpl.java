package com.news.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.news.dto.MoreDescriptionDto;
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
	public void save(MoreDescriptionDto desMore) {
		for(int i=0;i<desMore.getDescriptions().size();i++) {	
			Content des=new Content();
			des.setDescription(desMore.getDescriptions().get(i));
			des.setNews(newsRepos.findById(desMore.getIdNews()).orElse(null));
			desRepos.save(des);
		}
		return;
		
	}
	
}
