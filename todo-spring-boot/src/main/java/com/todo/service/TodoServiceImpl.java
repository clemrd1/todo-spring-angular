package com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.bean.Todo;
import com.todo.dao.TodoRepository;

public class TodoServiceImpl implements TodoService {
	@Autowired
	TodoRepository todoRepository;

	@Override
	public Todo updateTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo findTodo(Integer id) {
		return todoRepository.findOne(id);
	}

}
