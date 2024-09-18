package io.pn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.pn.dto.RegisterRequest;
import io.pn.dto.RegisterResponse;
import io.pn.entity.AppUserDetails;
import io.pn.repository.AppUserDetailsRepository;

@Service
public class AppUserServiceImpl implements AppUserService{
	
	@Autowired
	private AppUserDetailsRepository userRepo;
	
	

	@Override
	public Optional<AppUserDetails> getByUserName(String username) {
		List<AppUserDetails> byUsername = userRepo.findByUsername(username);
		Optional<AppUserDetails> first = byUsername.stream().findFirst();
		if(first.isPresent()) {
			return first;
		}
		return Optional.empty();
	}

	@Override
	public RegisterResponse saveUser(RegisterRequest request) {
		AppUserDetails aap = new AppUserDetails(request.username(),new BCryptPasswordEncoder().encode(request.password()), request.roles());
		AppUserDetails save = userRepo.save(aap);
		RegisterResponse response = new RegisterResponse(save.getPassword());
		return response;
	}

}
