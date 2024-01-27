import {Component} from '@angular/core';
import {BookAndOpinion} from "../../../interfaces/book-and-opinion";
import {UserService} from "../../../services/user/user.service";
import {LoginService} from "../../../services/auth/login.service";
import {FormBuilder, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {User} from "../../../interfaces/auth/user";
import {Opinion} from "../../../interfaces/opinion";
import {Router} from "@angular/router";

@Component({
  selector: 'app-books',
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './books.component.html',
  styleUrl: './books.component.css'
})
export class BooksComponent {
  errorMessage: String = "";
  userLogged: boolean = false;
  user?: User;
  private listaIdLibros: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20];
  bookAndOpinion: BookAndOpinion[] = [];
  opinionForm = this.formBuilder.group({
    comentario: ['', [Validators.required, Validators.minLength(1)]],
    calificacion: ['', [Validators.required, Validators.min(1), Validators.max(5)]]
  });
  searchTerm: string = '';

  constructor(
    private _userService: UserService,
    private _loginService: LoginService,
    private formBuilder: FormBuilder,
    private router: Router) {

    this._loginService.userLoggedIn.subscribe({
      next: (userLogged: boolean) => {
        this.userLogged = userLogged;
      }
    });

    this.loadBooks();

    let token = sessionStorage.getItem('token');

    if (this.userLogged && token) {
      this._userService.getUserData(token).subscribe({
        next: (userData: User) => {
          this.user = userData;
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => {
          console.info("Obtencion de datos del usuario completada.")
          console.log("Usuario: " + this.user?.nombre_Usuario);
        }
      });
    }

  }

  loadBooks() {
    for (let id in this.listaIdLibros) {
      this._userService.getBooksAndOpinions(this.listaIdLibros[id]).subscribe({
        next: (bookAndOpinion: BookAndOpinion) => {
          this.bookAndOpinion.push(bookAndOpinion);
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => {
          console.info("Obtención de libros y opiniones completa.");
        }
      });
    }
  }

  searchBooks() {
    if (this.searchTerm) {
      this.bookAndOpinion = this.bookAndOpinion.filter(book => book.book.titulo.toLowerCase().includes(this.searchTerm.toLowerCase()));
    } else {
      this.loadBooks();
    }
  }

  saveOpinion(libro_Id: number) {
    if (this.opinionForm.valid && this.opinionForm.value.calificacion != null && this.opinionForm.value.comentario != null) {
      let opinion: Opinion = {} as Opinion;
      opinion.usuario_Id = this.user?.usuario_Id as number;
      opinion.libro_Id = libro_Id;
      opinion.calificacion = +this.opinionForm.value.calificacion;
      opinion.comentario = this.opinionForm.value.comentario;
      opinion.fecha_Opinion = new Date();

      this._userService.saveOpinion(sessionStorage.getItem('token') as string, opinion).subscribe({
        next: (opinion: Opinion) => {
          console.log(opinion);
        },
        error: (error) => {
          console.error(error);
        },
        complete: () => {
          console.info("Opinión guardada.");
          this.opinionForm.reset();
          this.router.navigateByUrl('/books').then(r => console.log(r));
        }
      });
    }
  }

  get comment() {
    return this.opinionForm.controls.comentario;
  }

}
