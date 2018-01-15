package com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.bean.Todo;
import com.todo.dao.TodoRepository;

public class TodoServiceImpl implements TodoService {
	@Autowired
	TodoRepository todoRepository;

	@Override
	public Todo saveTodo(Todo todo) {
		return todoRepository.save(todo);
	}

	@Override
	public Todo findTodo(Integer id) {
		return todoRepository.findOne(id);
	}

	/* (non-Javadoc)
	 * @see com.todo.service.TodoService#deleteTodo(java.lang.Integer)
	 */
	@Override
	public void deleteTodo(Integer id) {
		Todo todo = todoRepository.findOne(id);
		if(todo!=null) {
			todoRepository.delete(todo);
		}
	}

}
