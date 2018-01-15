package com.todo.dao;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.bean.User;

/**
 * 
 */

/**
 * @author crenaudin
 *
 */
public interface UserRepository extends JpaRepository<User, String>{
	Optional<User> findByLogin(String login);
}
