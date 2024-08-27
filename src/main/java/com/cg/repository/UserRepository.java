package com.cg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.model.ApplicationUser;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

	    Optional<ApplicationUser> findByUsername(@Param("username") String username);
}
