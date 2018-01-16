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

  @Input() newTodo: Todo = new Todo();

  @Input() refreshTl: TodoList = new TodoList();

  newTodoDesc = '';

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

  addTodo(todoList: TodoList) {
    if (this.newTodoDesc.trim().length) {
      this.newTodo.description = this.newTodoDesc;
      this.todoService.addTodo(this.newTodo, todoList.listId).subscribe(
        newTodo => this.newTodo = newTodo, () => console.log('Error'), () => this.refreshTodoList(todoList)
      );
      this.newTodoDesc = '';
      this.newTodo = new Todo();
    }
  }

  deleteTodo(delTodo: Todo, todoList: TodoList) {
    this.todoService.deleteTodo(delTodo).subscribe();
    todoList.todos = todoList.todos.filter(todo => delTodo.todoId !== todo.todoId);
  }

  setCompleted(todo: Todo, todoList: TodoList) {
    console.log(todoList.listId);
    this.todoService.updateTodo(todo).subscribe(
      () => console.log('Completed'), () => console.log('Error'), () => this.refreshTodoList(todoList)
    );
  }

  refreshTodoList(tl: TodoList) {
    this.todoService.getTodoList(tl.listId).subscribe(
      tlUp => {
        let existingTl;
        existingTl = this.todoLists.find(todoList => todoList.listId === tlUp.listId);
        Object.assign(existingTl, tlUp);
      }
    );

  }

}
