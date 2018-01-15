import {TodoService} from '../todo.service';
import {TodoList} from '../todolist';
import {Todo} from '../todo';
import {UserService} from '../user.service';
import {Component, OnInit, Input} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit {
  @Input() todoLists: TodoList[];

  constructor(
    private route: ActivatedRoute,
    private todoService: TodoService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getTodos();
  }

  getTodos() {
    const login = this.route.snapshot.paramMap.get('login');
    this.todoService.getTodos(login)
      .subscribe(todoLists => this.todoLists = todoLists);
  }

  goBack(): void {
    this.location.back();
  }

  editTodo(todo: Todo) {
    todo.editing = true;
  }

  saveTodo(todo: Todo) {
    todo.editing = false;
    console.log(todo.todoId);
    console.log(todo.description);
    todo.description = todo.description.trim();
    this.todoService.updateTodo(todo).subscribe();
  }

  addTodo(description: string) {

  }

}
