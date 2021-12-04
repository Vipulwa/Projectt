package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

	public Comment findByComplaintId(int complaintId);
}
