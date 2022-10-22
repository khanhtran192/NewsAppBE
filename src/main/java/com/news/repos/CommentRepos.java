package com.news.repos;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.news.entity.Comment;

@Repository
public interface CommentRepos extends JpaRepository<Comment, Long> {
//	@Query(value="select * from Comment c order by c.createdTime DESC",nativeQuery = true)
	@Query(value = "SELECT c FROM Comment c order by c.createdTime DESC ")
	List<Comment> findById();
	
	Page<Comment> findAllByNewsId(long id,Pageable pageable);
}
