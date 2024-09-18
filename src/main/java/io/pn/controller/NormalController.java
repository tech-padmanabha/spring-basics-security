package io.pn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pn.dto.RegisterRequest;
import io.pn.dto.RegisterResponse;
import io.pn.service.AppUserService;

@RestController
@RequestMapping("/api")
public class NormalController {
	
	@Autowired
	private AppUserService appUserService;
	
	@GetMapping("/welcome")
	public List<String> welcome() {
		return List.of("USER","ADMIN","READ");
	}
	
	@PostMapping("/user")
	public ResponseEntity<RegisterResponse> addRequest(@RequestBody RegisterRequest request) {
		RegisterResponse saveUser = appUserService.saveUser(request);
		return new ResponseEntity<RegisterResponse>(saveUser,HttpStatus.CREATED);
	}
	

}


