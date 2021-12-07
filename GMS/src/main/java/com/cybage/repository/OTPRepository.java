package com.cybage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cybage.model.OTP;

public interface OTPRepository extends JpaRepository<OTP, Integer> {

	OTP findByUserId(int id);

}
