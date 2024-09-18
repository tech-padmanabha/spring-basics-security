package io.pn.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.pn.dto.LoginRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String getUserAuth() {

		return "Welcome to user";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String getAdminAuth() {

		return "Welcome to Admin";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/request")
	public String authRequest(@RequestBody LoginRequest request) {
		return "Welcome" + request.toString();
	}
}
