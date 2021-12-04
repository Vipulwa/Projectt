package com.cybage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cybage.model.AccountStatus;
import com.cybage.model.Feedback;
import com.cybage.model.Role;
import com.cybage.model.User;
import com.cybage.repository.CitizenRepository;

@Service
@Transactional
public class UserServiceImpl implements IUserService {
	
	@Autowired
	CitizenRepository citizenRepository;
	private final RestTemplate restTemplate;
	public UserServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(null));
		messageConverters.add(converter);
		this.restTemplate.setMessageConverters(messageConverters);
	}
	

	@Override
	public ResponseEntity<?> findByEmailAndPassword(String email,String password) {
		
		User user=citizenRepository.findByEmailAndPassword(email,password);
		
		if(user==null) {
			User citizen=citizenRepository.findByEmail(email);
			if(citizen==null) {
				return ResponseEntity.ok("Entered Email is not registered in system");
			}
			if(citizen.getCounter()<=1) {
				int counter=citizen.getCounter();
				counter++;
				citizen.setCounter(counter);
				citizenRepository.save(citizen);
				return ResponseEntity.ok("Please enter valid credentials");
			}		
			
			else{
				System.out.println("INSiDE");
				if( citizen.getRole().equals(Role.ADMIN)){
				return ResponseEntity.ok("Enter Right credentials");
				}
				citizen.setStatus(AccountStatus.LOCKED);
				citizenRepository.save(citizen);
				return ResponseEntity.ok("Your account has been block due to multiple bad credentials");
				
			}
		}
		else if(user.getStatus().equals(AccountStatus.UNLOCKED ) ) {
			user.setCounter(0);
			user=citizenRepository.save(user);
			return ResponseEntity.ok(user);
		}
		return  ResponseEntity.ok(" Sry..We can not proceed further..your account is blocked");
		
		
		
		
	}

	@Override
	public ResponseEntity<?> addFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		String uri = "http://localhost:8082/feedback/add";
		RestTemplate restTemplate = new RestTemplate();
	    String result = restTemplate.postForObject(uri, feedback,String.class);
	    return ResponseEntity.ok(result);
	}

	@Override
	public ResponseEntity<?> getAllFeedback() {
		// TODO Auto-generated method stub
		String uri = "http://localhost:8082/feedback/getAll";
		RestTemplate restTemplate = new RestTemplate();
		List<Feedback>list= restTemplate.getForObject(uri,List.class);
		if(list==null) {
			return ResponseEntity.ok("No record Found");
		}
		else {
			return ResponseEntity.ok(list);
		}
		
	}

}
