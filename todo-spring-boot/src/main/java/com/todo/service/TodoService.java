package com.todo.service;

import com.todo.bean.Todo;

public interface TodoService {
	Todo updateTodo(Todo todo);
	
	Todo findTodo(Integer id);
}
