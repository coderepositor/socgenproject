package com.socgen.crudapi;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.socgen.crudapi.entity.Role;
import com.socgen.crudapi.entity.User;
import com.socgen.crudapi.repository.RoleRepository;

@SpringBootTest
class UserManagementApplicationTests {

	@Autowired
	private RoleRepository roleRepository;
		
	@Test
	void SaveRole() {
		User user = new User();
		user.setFirstName("Vighnesh");
		user.setLastName("Karuna");
		user.setEmail("vg@gmail.com");
		user.setPassword("Secret");
		
		User admin = new User();
		admin.setFirstName("Rebecca");
		admin.setLastName("Logo");
		admin.setEmail("lr@gmail.com");
		admin.setPassword("Secret12");
		
		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN_SOCGEN");
		
		roleAdmin.getUsers().add(user);
		roleAdmin.getUsers().add(admin);
		
		roleRepository.save(roleAdmin);
	}
	
	@Test
	void fetchRole() {
		List<Role> roles = roleRepository.findAll();
		roles.forEach((r)->{
			System.out.println(r.getName());
			r.getUsers().forEach((u)->{
				System.out.println(u.getFirstName());
			});
		});
	}

}
