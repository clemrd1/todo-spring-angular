import {MessageService} from './message.service';
import {User} from './user';
import {HttpHeaders} from '@angular/common/http';
import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';
import {of} from 'rxjs/observable/of';
import {catchError, map, tap} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable()
export class UserService {
  private baseUrl = 'http://localhost:8080/todo-spring-boot';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) {}

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl + '/users')
      .pipe(
      tap(heroes => this.log(`fetched users`)),
      catchError(this.handleError('getUsers', []))
      );
  }

  getUser(login: string): Observable<User> {
    const url = `${this.baseUrl}/${login}`;
    return this.http.get<User>(url).pipe(
      tap(_ => this.log(`fetched user id=${login}`)),
      catchError(this.handleError<User>(`getUser id=${login}`))
    );
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(`${this.baseUrl}/${user.login}`, user, httpOptions).pipe(
      tap(_ => this.log(`updated user id=${user.login}`)),
      catchError(this.handleError<any>('updateUser'))
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
    this.messageService.add('UserService: ' + message);
  }
}
