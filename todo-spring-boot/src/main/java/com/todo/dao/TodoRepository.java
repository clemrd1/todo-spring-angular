package com.todo.dao;

import org.springframework.data.repository.CrudRepository;

import com.todo.bean.Todo;

public interface TodoRepository extends CrudRepository<Todo, Integer>{

}
