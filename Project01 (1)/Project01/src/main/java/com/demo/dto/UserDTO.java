package com.demo.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ST_USER")
public class UserDTO {

	@Id
	@GeneratedValue(generator = "demo")
	@GenericGenerator(name = "demo", strategy = "native")
	@Column(name = "ID", unique = true, nullable = false)
	private Long id;

	@Column(name = "USERNAME", length = 50)
	private String username;

	@Column(name = "EMAIL", length = 100)
	private String email;

	@Column(name = "PASSWORD", length = 100)
	private String password;

	
    @Column(name = "ROLE_NAME", length = 50) 
	private String roleName = null;
	 
	@Column(name = "ROLE_ID")
	private Long roleId;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getKey() {
		return id + "";
	}

	
}
