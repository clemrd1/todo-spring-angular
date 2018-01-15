/**
 * 
 */
package com.todo.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.bean.TodoList;
import com.todo.bean.User;
import com.todo.dao.TodoListRepository;

/**
 * @author crenaudin
 *
 */
public class TodoListServiceImpl implements TodoListService{
	
	@Autowired
	TodoListRepository todoListRepository;
	
	@Autowired
	UserService userService;

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findTodoListByUser(java.lang.String)
	 */
	@Override
	public Set<TodoList> findTodoListByUser(String login) {
		User user = userService.findUserByLogin(login);
		return todoListRepository.findTodoListByUsersOrderByIdDesc(user);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findActivedTodoListByUsers(java.lang.String)
	 */
	@Override
	public Set<TodoList> findActivedTodoListByUsers(String login) {
		User user = userService.findUserByLogin(login);
		return todoListRepository.findTodoListByUsersAndArchivedOrderByIdDesc(user, false);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findArchivedTodoListByUsers(java.lang.String)
	 */
	@Override
	public Set<TodoList> findArchivedTodoListByUsers(String login) {
		User user = userService.findUserByLogin(login);
		return todoListRepository.findTodoListByUsersAndArchivedOrderByIdDesc(user, true);
	}
}
