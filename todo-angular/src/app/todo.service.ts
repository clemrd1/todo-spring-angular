import {MessageService} from './message.service';
import {Todo} from './todo';
import {TodoList} from './todolist';
import {HttpClient} from '@angular/common/http';
import {HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, map, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};

@Injectable()
export class TodoService {

  private baseUrl = 'http://localhost:8080/todo-spring-boot';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  getTodos(login: string): Observable<TodoList[]> {
    const url = `${this.baseUrl}/${login}/todos`;
    return this.http.get<TodoList[]>(url).pipe(
      tap(_ => this.log(`fetched todos user id=${login}`)),
      catchError(this.handleError<TodoList[]>(`getTodos id=${login}`))
    );
  }

  updateTodo(todo: Todo): Observable<any> {
    return this.http.put(`${this.baseUrl}/todo/${todo.todoId}`, todo, httpOptions).pipe(
      tap(_ => this.log(`updated todo id=${todo.todoId}`)),
      catchError(this.handleError<any>('updateTodo'))
    );
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add('TodoService: ' + message);
  }

}
