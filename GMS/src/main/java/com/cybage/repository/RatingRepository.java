package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.Rating;

public interface RatingRepository extends JpaRepository<Rating, Integer> {

	List<Rating> findByComplaintId(int complaintId);

}
