package com.contacts.demo.repository;

import com.contacts.demo.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByUsername(String username);

}
