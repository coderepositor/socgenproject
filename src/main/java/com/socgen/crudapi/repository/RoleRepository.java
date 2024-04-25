package com.socgen.crudapi.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.socgen.crudapi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}