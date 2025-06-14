package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "demo_user_001")
public class DemoUser {
	
	@Id
	@Column(name="id")
	Integer id;
	
	@Column(name="username")
	String name;
	
	@Column(name="pword")
	String pword;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPword() {
		return pword;
	}

	public void setPword(String pword) {
		this.pword = pword;
	}

	@Override
	public String toString() {
		return "DemoUser [id=" + id + ", name=" + name + ", pword=" + pword + "]";
	}
	
}