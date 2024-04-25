package com.socgen.crudapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.crudapi.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
	}
