/**
 * 
 */
package com.todo.dao;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.bean.TodoList;
import com.todo.bean.User;

/**
 * @author crenaudin
 *
 */
public interface TodoListRepository extends JpaRepository<TodoList, Integer>{
	Set<TodoList> findTodoListByUsersOrderByIdDesc(User user);
	
	Set<TodoList> findTodoListByUsersAndArchivedOrderByIdDesc(User user, boolean archived);
	
}
