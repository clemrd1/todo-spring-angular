package com.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.todo.service.TodoListService;
import com.todo.service.TodoListServiceImpl;
import com.todo.service.TodoService;
import com.todo.service.TodoServiceImpl;
import com.todo.service.UserService;
import com.todo.service.UserServiceImpl;

@SpringBootApplication
public class TodoSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TodoSpringBootApplication.class, args);
	}
	
	@Bean
    public UserService userService() {
        return new UserServiceImpl ();
    }
	
	@Bean
    public TodoListService todoListService() {
        return new TodoListServiceImpl ();
    }
	
	@Bean
	public TodoService todoService() {
		return new TodoServiceImpl();
	}
}
