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
		return todoListRepository.findTodoListByUsersOrderByListIdDesc(user);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findActivedTodoListByUsers(java.lang.String)
	 */
	@Override
	public Set<TodoList> findActivedTodoListByUsers(String login) {
		User user = userService.findUserByLogin(login);
		return todoListRepository.findTodoListByUsersAndArchivedOrderByListIdDesc(user, false);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findArchivedTodoListByUsers(java.lang.String)
	 */
	@Override
	public Set<TodoList> findArchivedTodoListByUsers(String login) {
		User user = userService.findUserByLogin(login);
		return todoListRepository.findTodoListByUsersAndArchivedOrderByListIdDesc(user, true);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#findTodoList(java.lang.Integer)
	 */
	@Override
	public TodoList findTodoList(Integer id) {
		return todoListRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#updateTodoList(com.todo.bean.TodoList)
	 */
	@Override
	public TodoList updateTodoList(TodoList tl) {
		return todoListRepository.save(tl);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoListService#deleteTodoList(java.lang.Integer)
	 */
	@Override
	public void deleteTodoList(Integer id) {
		TodoList tl = todoListRepository.findOne(id);
		if(tl!=null) {
			Set<User> users = tl.getUsers();
			for(User user : users) {
				user.getTodoLists().remove(tl);
				userService.updateUser(user);
			}
			todoListRepository.delete(tl);
		}
	}
}
