package com.todo.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.todo.bean.Todo;
import com.todo.bean.TodoList;
import com.todo.dao.TodoRepository;

public class TodoServiceImpl implements TodoService {
	@Autowired
	TodoRepository todoRepository;
	
	@Autowired
	TodoListService todoListService;

	@Override
	public Todo updateTodo(Todo todo) {
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
		System.out.println(id);
		Todo todo = todoRepository.findOne(id);
		if(todo!=null) {
			TodoList tl = todo.getTodoList();
			tl.getTodos().remove(todo);
			todoListService.updateTodoList(tl);
			System.out.println("todo not null");
			todoRepository.delete(todo);
		}
	}

}
