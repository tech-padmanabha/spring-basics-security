package io.pn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.pn.entity.AppUserDetails;


@Repository
public interface AppUserDetailsRepository extends JpaRepository<AppUserDetails, Long>{

	List<AppUserDetails> findByUsername(String username);
	
}
