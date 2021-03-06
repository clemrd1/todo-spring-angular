package com.todo.controller;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.bean.Todo;
import com.todo.bean.TodoList;
import com.todo.bean.User;
import com.todo.service.TodoListService;
import com.todo.service.TodoService;
import com.todo.service.UserService;

/**
 * @author crenaudin
 *
 */

@RestController
@RequestMapping
@CrossOrigin(origins = "http://localhost:4200")
public class TodoController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	TodoListService todoListService;
	
	@Autowired
	TodoService todoService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{login}")
	public User getUserByLogin(@PathVariable("login") String login) {
		return userService.findUserByLogin(login);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{login}")
	public ResponseEntity<?> updateUser(@PathVariable("login") String login, @Valid @RequestBody User user) {
		userService.validateUser(login);
		System.out.println(user.toString());
		User userToUp = userService.findUserByLogin(login);
		userToUp.setLastname(user.getLastname());
		userToUp.setFirstname(user.getFirstname());
		userToUp.setEmail(user.getEmail());
		userToUp.setAddress(user.getAddress());
		userToUp.setCp(user.getCp());
		userToUp.setCity(user.getCity());
		userService.updateUser(userToUp);
		return new ResponseEntity<User>(userToUp, HttpStatus.OK);
	}
	
	
	@GetMapping(path="/{login}/todos")
	public @ResponseBody Set<TodoList> getTodoListByUsers(@PathVariable("login") String login) {
		return todoListService.findActivedTodoListByUsers(login);
	}
	
	@GetMapping(path="/{login}/todos/archived")
	public @ResponseBody Set<TodoList> getArvhivedTodoListByUsers(@PathVariable("login") String login) {
		return todoListService.findArchivedTodoListByUsers(login);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/todos/{listId}")
	public TodoList getTodoList(@PathVariable("listId") Integer listId) {
		return todoListService.findTodoList(listId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/todos/{listId}")
	public boolean deleteTodoList(@PathVariable("listId") Integer listId) {
		todoListService.deleteTodoList(listId);
		return true;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/todos/{login}")
	public ResponseEntity<?> addTodoList(@PathVariable("login") String login, @RequestBody TodoList tl){
		userService.validateUser(login);
		User u = userService.findUserByLogin(login);
		TodoList newTl = new TodoList();
		newTl.setName(tl.getName());
		newTl.getUsers().add(u);
		newTl = todoListService.updateTodoList(newTl);
		u.getTodoLists().add(newTl);
		userService.updateUser(u);
		return new ResponseEntity<TodoList>(newTl, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/todo/{listId}")
	public ResponseEntity<?> addTodo(@PathVariable("listId") Integer listId, @RequestBody Todo todo) {
		System.out.println("addTodo");
		TodoList tl = todoListService.findTodoList(listId);
		Todo newTodo = new Todo();
		newTodo.setDescription(todo.getDescription());
		newTodo.setTodoList(tl);
		newTodo = todoService.updateTodo(newTodo);
		return new ResponseEntity<Todo>(newTodo, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/todo/{id}")
	public ResponseEntity<?> updateTodo(@PathVariable("id") Integer id, @Valid @RequestBody Todo todo){
		Todo todoToUp = todoService.findTodo(id);
		todoToUp.setDescription(todo.getDescription());
		todoToUp.setCompleted(todo.isCompleted());
		if(todoToUp.isCompleted() && !todo.isCompleted()) {
			todoToUp.setCompletedDate(null);
		}else if(todo.isCompleted() && !todoToUp.isCompleted()) {
			todoToUp.setCompletedDate(new Date());
		}
		todoService.updateTodo(todoToUp);
		return new ResponseEntity<Todo>(todoToUp, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/todo/{id}")
	public boolean delete(@PathVariable("id") Integer id) {
		todoService.deleteTodo(id);
		return true;
	}
	
}
