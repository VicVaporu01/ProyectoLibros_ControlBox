import {Component} from '@angular/core';
import {User} from "../../../interfaces/auth/user";
import {UserService} from "../../../services/user/user.service";
import {LoginService} from "../../../services/auth/login.service";
import {UserAndOpinion} from "../../../interfaces/user-and-opinion";

@Component({
  selector: 'app-personal-details',
  standalone: true,
  imports: [],
  templateUrl: './personal-details.component.html',
  styleUrl: './personal-details.component.css'
})
export class PersonalDetailsComponent {
  errorMessage: String = "";
  userLogged: boolean = false;
  edditMode: boolean = false;
  user?: User;
  userAndOpinion: UserAndOpinion[] = [];

  constructor(private _userService: UserService, private _loginService: LoginService) {

    let token = sessionStorage.getItem('token');

    if (token) {
      this._userService.getUserData(token).subscribe({
        next: (userData: User) => {
          this.user = userData;
        },
        error: (error) => {
          console.log(error);
        },
        complete: () => {
          console.info("Obtención de datos de usuario completa");
          this.usuarioObtenido(token as string)
        }
      });
    } else {
      // Handle the case where usuario_Id is null
      console.error('No token found');
    }

    this._loginService.userLoggedIn.subscribe({
      next: (userLogged: boolean) => {
        this.userLogged = userLogged;
      }
    });

  }

  usuarioObtenido(token: string) {
    this._userService.getUserOpinions(this.user?.usuario_Id as number, token).subscribe({
      next: (userAndOpinion: UserAndOpinion[]) => {
        this.userAndOpinion = userAndOpinion;
      }
    });
  }

}
