import {TodoService} from '../todo.service';
import {TodoList} from '../todolist';
import {Todo} from '../todo';
import {UserService} from '../user.service';
import {Component, OnInit, Input, Renderer2, AfterViewInit, AfterContentChecked} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';

@Component({
  selector: 'app-todos',
  templateUrl: './todos.component.html',
  styleUrls: ['./todos.component.css']
})
export class TodosComponent implements OnInit, AfterContentChecked {

  @Input() todoLists: TodoList[];

  @Input() newTodo: Todo = new Todo();

  @Input() refreshTl: TodoList = new TodoList();

  @Input() newTl: TodoList = new TodoList();

  newTodoDesc = '';

  newTodoListName = '';

  constructor(
    private route: ActivatedRoute,
    private todoService: TodoService,
    private location: Location,
    private renderer: Renderer2
  ) {}

  ngOnInit() {
    this.getTodos();
  }

  ngAfterContentChecked(): void {
    this.setFocus();
  }

  setFocus() {
    // TODO: check for better solution
    try {
      this.renderer.selectRootElement('#editedTodo').focus();
    } catch (e) {
    }
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
    if (!todo.completed) {
      todo.editing = true;
    }
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

  createNewTl() {
    this.todoLists.unshift(new TodoList());
  }

  addTodoList() {
    const login = this.route.snapshot.paramMap.get('login');
    if (this.newTodoListName.trim().length) {
      this.newTl.name = this.newTodoListName;
      this.todoService.addTodoList(this.newTl, login).subscribe(
        newTl => this.newTl = newTl, () => console.log('Error'), () => this.getTodos()
      );
    }
  }

  editTodoList(tl: TodoList) {
    tl.editing = true;
  }

  deleteTodoList(delTl: TodoList) {
    this.todoService.deleteTodoList(delTl).subscribe();
    this.todoLists = this.todoLists.filter(tl => delTl.listId !== tl.listId);
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
