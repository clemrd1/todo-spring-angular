package com.todo.service;

import com.todo.bean.Todo;

public interface TodoService {
	Todo saveTodo(Todo todo);
	
	Todo findTodo(Integer id);
	
	void deleteTodo(Integer id);
}
