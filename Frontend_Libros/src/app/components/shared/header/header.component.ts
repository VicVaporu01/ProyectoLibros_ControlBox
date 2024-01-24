import {Component, OnDestroy, OnInit} from '@angular/core';
import {RouterLink} from "@angular/router";
import {LoginService} from "../../../services/auth/login.service";

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  userLoggedIn: boolean = false;

  constructor(private loginService: LoginService) {
  }

  ngOnInit(): void {
    this.loginService.currentUserLoggedIn.subscribe({
      next: (userLoggedIn: boolean) => {
        this.userLoggedIn = userLoggedIn;
      }
    })
  }

  logout(): void {
    this.loginService.logout();
  }

}
