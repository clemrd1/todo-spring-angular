package com.todo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todo.bean.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer>{

}
