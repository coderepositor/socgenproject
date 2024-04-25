package com.socgen.crudapi.entity;
//
//
//
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//@Table(name="users")
//public class User {
//@Id
//@GeneratedValue(strategy=GenerationType.IDENTITY)
//private Long id;
//@Column(nullable=false)
//private String firstName;
//@Column(nullable=false)
//private String lastName;
//@Column(nullable=false, unique = true)
//private String email;
//
//public long getId() {
//	return id;
//}
//public String getFirstName() {
//	return firstName;
//}
//public void setFirstName(String firstName) {
//	this.firstName = firstName;
//}
//public String getLastName() {
//	return lastName;
//}
//public void setLastName(String lastName) {
//	this.lastName = lastName;
//}
//public String getEmail() {
//	return email;
//}
//public void setEmail(String email) {
//	this.email = email;
//}
//public void setId(Long id) {
//	this.id = id;
//}


//}

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "unique_email",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id"
            )
    )
    private Set<Role> roles = new HashSet<>();
}