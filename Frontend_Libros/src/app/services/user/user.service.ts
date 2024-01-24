import {Injectable} from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {User} from "../../interfaces/auth/user";
import {environment} from "../../../environments/environment.development";
import {UserAndOpinion} from "../../interfaces/user-and-opinion";
import {BookAndOpinion} from "../../interfaces/book-and-opinion";
import {Opinion} from "../../interfaces/opinion";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) {
  }

  getUserData(token: string): Observable<User> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get<User>(environment.urlAPI + "user/logged", {headers}).pipe(
      catchError(this.errorHandler)
    );
  }

  getUserOpinions(id: number, token: string): Observable<UserAndOpinion[]> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.get<UserAndOpinion[]>(environment.urlAPI + "user/opinions/" + id, {headers}).pipe(
      catchError(this.errorHandler)
    );
  }

  getBooksAndOpinions(id: number): Observable<BookAndOpinion> {
    return this.httpClient.get<BookAndOpinion>(environment.urlHost + "auth/books/" + id).pipe(
      catchError(this.errorHandler)
    );
  }

  saveOpinion(token: string, opinion: Opinion): Observable<Opinion> {
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpClient.post<Opinion>(environment.urlAPI + "user/saveOpinion", opinion, {headers}).pipe(
      catchError(this.errorHandler)
    );
  }

  private errorHandler(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se produjo un error ', error.error);
    } else {
      console.error('Error de la API: ', error.status, error.error);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }

}
