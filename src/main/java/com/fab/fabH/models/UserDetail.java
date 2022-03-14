package com.fab.fabH.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserDetail {

	public UserDetail() {
	}

 	@Id
  	@GeneratedValue(strategy=GenerationType.AUTO)

  	private Integer id;

  	private String username;

  	private String password;

	private Long phoneNumber;

	private String address;

	private Boolean active;

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getUsername() {
			return username;
		}

	public void setUsername(String username) {
			this.username = username;
		}

	  
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}



}
