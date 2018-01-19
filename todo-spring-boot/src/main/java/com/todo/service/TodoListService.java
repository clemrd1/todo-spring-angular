/**
 * 
 */
package com.todo.service;

import java.util.Set;

import com.todo.bean.TodoList;

/**
 * @author crenaudin
 *
 */
public interface TodoListService {
	Set<TodoList> findTodoListByUser(String login);
	
	Set<TodoList> findActivedTodoListByUsers(String login);
	
	Set<TodoList> findArchivedTodoListByUsers(String login);
	
	TodoList findTodoList(Integer id);
	
	TodoList updateTodoList(TodoList tl);
	
	void deleteTodoList(Integer id);
	
	
}
