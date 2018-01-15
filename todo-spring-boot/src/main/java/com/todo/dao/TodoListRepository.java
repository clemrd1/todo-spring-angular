/**
 * 
 */
package com.todo.dao;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.todo.bean.TodoList;
import com.todo.bean.User;

/**
 * @author crenaudin
 *
 */
public interface TodoListRepository extends CrudRepository<TodoList, Integer>{
	Set<TodoList> findTodoListByUsers(User user);
	
	Set<TodoList> findTodoListByUsersAndArchived(User user, boolean archived);
	
}
