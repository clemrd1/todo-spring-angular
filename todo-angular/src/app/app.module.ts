import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {UserComponent} from './user/user.component';

import {UserService} from './user.service';
import {AppRoutingModule} from './app-routing.module';
import {MessageService} from './message.service';
import {TodoService} from './todo.service';
import {HttpClientModule} from '@angular/common/http';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {FormsModule} from '@angular/forms';
import {TodosComponent} from './todos/todos.component';
import {MatGridListModule, MatCardModule, MatButtonModule, MatInputModule} from '@angular/material';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    UserComponent,
    UserDetailComponent,
    TodosComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatGridListModule,
    MatCardModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatInputModule
  ],
  providers: [UserService, MessageService, TodoService],
  bootstrap: [AppComponent]
})
export class AppModule {}
