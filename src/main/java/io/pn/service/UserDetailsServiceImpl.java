package io.pn.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import io.pn.entity.AppUserDetails;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private AppUserService appUserService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return getUserDetailsByUsername(username);
	}
	
	
	private UserDetails getUserDetailsByUsername(String username) {
		Optional<AppUserDetails> userByUserName = appUserService.getByUserName(username);
		if(userByUserName.isPresent()) {
			AppUserDetails user = userByUserName.get();
			return new UserDetails() {
				
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getUsername() {
					// TODO Auto-generated method stub
					return user.getUsername();
				}
				
				@Override
				public String getPassword() {
					// TODO Auto-generated method stub
					return user.getPassword();
				}
				
				@Override
				public Collection<? extends GrantedAuthority> getAuthorities() {
					List<String> roles = user.getRoles();
					List<SimpleGrantedAuthority> grantAuth = new ArrayList<>();
					roles.forEach(role -> grantAuth.add(new SimpleGrantedAuthority("ROLE_"+role)));
					return grantAuth;
				}
			};
		}
		else {
			throw new UsernameNotFoundException("User not Available");
		}
	}
}
