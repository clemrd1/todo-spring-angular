package com.todo.dao;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.todo.bean.User;

/**
 * 
 */

/**
 * @author crenaudin
 *
 */
public interface UserRepository extends CrudRepository<User, String>{
	Optional<User> findByLogin(String login);
}
