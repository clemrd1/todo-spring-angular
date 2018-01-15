/**
 * 
 */
package com.todo.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.bean.TodoList;
import com.todo.bean.User;

/**
 * @author crenaudin
 *
 */
public interface TodoListRepository extends JpaRepository<TodoList, Integer>{
	Set<TodoList> findTodoListByUsers(User user);
	
	Set<TodoList> findTodoListByUsersAndArchived(User user, boolean archived);
	
}
