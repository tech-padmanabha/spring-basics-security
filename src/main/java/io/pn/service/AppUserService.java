package io.pn.service;

import java.util.Optional;

import io.pn.dto.RegisterRequest;
import io.pn.dto.RegisterResponse;
import io.pn.entity.AppUserDetails;

public interface AppUserService {
	Optional<AppUserDetails> getByUserName(String username);
	
	RegisterResponse saveUser(RegisterRequest request);
}
