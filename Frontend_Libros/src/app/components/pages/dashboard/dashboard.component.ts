import {Component, OnInit} from '@angular/core';
import {LoginService} from "../../../services/auth/login.service";
import {PersonalDetailsComponent} from "../../user/personal-details/personal-details.component";
import {BooksComponent} from "../books/books.component";

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    PersonalDetailsComponent,
    BooksComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {

  userLoggedIn: boolean = false;

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.currentUserLoggedIn.subscribe({
      next: (userLoggedIn: boolean) => {
        this.userLoggedIn = userLoggedIn;
      }
    });

  }

}
