import {Routes} from '@angular/router';
import {DashboardComponent} from "./components/pages/dashboard/dashboard.component";
import {LoginComponent} from "./components/auth/login/login.component";
import {RegisterComponent} from "./components/auth/register/register.component";
import {BooksComponent} from "./components/pages/books/books.component";
import {PersonalDetailsComponent} from "./components/user/personal-details/personal-details.component";

export const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: DashboardComponent
  },
  {
    path: 'books', component: BooksComponent
  },
  {
    path: 'login', component: LoginComponent
  },
  {
    path: 'register', component: RegisterComponent
  },
  {
    path: 'user', component: PersonalDetailsComponent
  },
  {
    path: '**', redirectTo: '/home', pathMatch: 'full'
  }
];
