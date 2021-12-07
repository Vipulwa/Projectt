package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public List<Comment> findByComplaintId(int complaintId);
}
