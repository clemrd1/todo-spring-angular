/**
 * 
 */
package com.todo.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author crenaudin
 *
 */
@Entity
@Table(name="todo_list")
public class TodoList {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer listId;
	
	private String name;
	
	private boolean archived;
	
	@Column(name="archived_date")
	private Date archivedDate;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "todoLists")
    private Set<User> users = new HashSet<User>();
	
	@OneToMany(mappedBy = "todoList", fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@OrderBy("todoId desc")
    private Set<Todo> todos = new HashSet<Todo>();

	public Integer getListId() {
		return listId;
	}

	public void setListId(Integer listId) {
		this.listId = listId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public Date getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Date archivedDate) {
		this.archivedDate = archivedDate;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Todo> getTodos() {
		return todos;
	}

	public void setTodos(Set<Todo> todos) {
		this.todos = todos;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TodoList [listId=").append(listId).append(", name=").append(name).append(", archived=")
				.append(archived).append(", archivedDate=").append(archivedDate).append("]");
		return builder.toString();
	}
	
}
