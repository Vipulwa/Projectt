package com.cybage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cybage.model.LikeDislike;

public interface LikeDislikeRepository extends JpaRepository<LikeDislike, Integer> {

	List<LikeDislike> findBycomplaintId(int complaintId);
	
	@Query("select sum(l.likes) from LikeDislike l where l.complaint.id=?1 group by l.complaint ")
	long findLikeCountByComplaintNo(int complaintId);

	
	@Query("select sum(l.dislike) from LikeDislike l where l.complaint.id=?1 group by l.complaint ")
	long findDislikeCountByComplaintNo(int complaintId);

	LikeDislike findByUserIdAndComplaintId(int userId, int complaintId);
	
//	@Query(value = "select * from like_dislike where complaint_id=? and user_id=?",nativeQuery = true)
//	public List<LikeDislike> getAllLikesAndDislikes(int complaintId,int userId);

}
