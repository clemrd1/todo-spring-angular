import { TodosComponent } from './todos/todos.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import {UserComponent} from './user/user.component';
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';

const routes: Routes = [
  { path: '', redirectTo: '/users', pathMatch: 'full' },
  { path: 'users', component: UserComponent},
  { path: 'detail/:login', component: UserDetailComponent },
  { path: ':login/todos', component: TodosComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
