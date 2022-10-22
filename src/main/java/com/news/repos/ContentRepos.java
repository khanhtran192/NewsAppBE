package com.news.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.news.entity.Content;

@Repository
public interface ContentRepos extends JpaRepository<Content, Long>{

}
