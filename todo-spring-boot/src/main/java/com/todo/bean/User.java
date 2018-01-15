package com.todo.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="user")
public class User {

	@Id
	@Size(min=5, max=50)
	private String login;
	
	@JsonIgnore
	@Size(min=5, max=32)
	private String password;
	
	@Size(min=0, max=100)
	private String lastname;
	
	@Size(min=0, max=100)
	private String firstname;
	
	@Size(min=0, max=150)
	private String email;
	
	@Size(min=0, max=250)
	private String address;
	
	@Size(min=5, max=5)
	private String cp;
	
	@Size(min=5, max=100)
	private String city;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
	        name = "todo_list_user", 
	        joinColumns = { @JoinColumn(name = "login") }, 
	        inverseJoinColumns = { @JoinColumn(name = "todo_list_id") })
    private Set<TodoList> todoLists = new HashSet<TodoList>();
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Set<TodoList> getTodoLists() {
		return todoLists;
	}

	public void setTodoLists(Set<TodoList> todoLists) {
		this.todoLists = todoLists;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [login=").append(login).append(", lastname=").append(lastname).append(", firstname=")
				.append(firstname).append(", email=").append(email).append(", address=").append(address).append(", cp=")
				.append(cp).append(", city=").append(city).append("]");
		return builder.toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) {
			return true;
		}
		
		if(!(o instanceof User)) {
			return false;
		}
		
		final User user = (User)o;
		if(!login.equals(user.getLogin())) {
			return false;
		}
		return true;
	}

}
