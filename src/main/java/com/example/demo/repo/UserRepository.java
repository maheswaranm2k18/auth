package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.DemoUser;

@Repository
public interface UserRepository extends JpaRepository<DemoUser, Integer> {

	DemoUser findByName(String username);

}
