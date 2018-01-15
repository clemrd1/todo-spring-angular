/**
 * 
 */
package com.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.bean.User;
import com.todo.dao.UserRepository;
import com.todo.exception.UserNotFoundException;

/**
 * @author crenaudin
 *
 */
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;

	/* (non-Javadoc)
	 * @see com.todo.service.UserService#findUserByLogin(java.lang.String)
	 */
	@Override
	public User findUserByLogin(String login) {
		validateUser(login);
		return userRepository.findOne(login);
	}
	
	@Override
	public void validateUser(String login) {
		userRepository.findByLogin(login).orElseThrow(
				() -> new UserNotFoundException(login));
	}

	/* (non-Javadoc)
	 * @see com.todo.service.UserService#getUsers()
	 */
	@Override
	public List<User> getUsers() {
		return (List<User>) userRepository.findAll();
	}

	/* (non-Javadoc)
	 * @see com.todo.service.UserService#updateUser(com.todo.bean.User)
	 */
	@Override
	public User updateUser(User user) {
		System.out.println(user.toString());
		return userRepository.save(user);
	}
	
	
}
