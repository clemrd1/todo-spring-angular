package com.todo.service;
import java.util.List;

import com.todo.bean.User;

/**
 * 
 */

/**
 * @author crenaudin
 *
 */
public interface UserService {
	User findUserByLogin(String login);
	
	List<User> getUsers();
	
	User updateUser(User user);
	
	void validateUser(String login);
}
