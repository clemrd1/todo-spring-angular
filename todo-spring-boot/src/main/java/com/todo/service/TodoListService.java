/**
 * 
 */
package com.todo.service;

import java.util.Set;

import com.todo.bean.TodoList;
import com.todo.bean.User;

/**
 * @author crenaudin
 *
 */
public interface TodoListService {
	Set<TodoList> findTodoListByUser(String login);
	
	Set<TodoList> findActivedTodoListByUsers(String login);
	
	Set<TodoList> findArchivedTodoListByUsers(String login);
}
