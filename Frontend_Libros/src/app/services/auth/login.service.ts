import {Injectable} from '@angular/core';
import {LoginRequest} from "../../interfaces/auth/login-request";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {BehaviorSubject, catchError, map, Observable, tap, throwError} from "rxjs";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  currentUserLoggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

  constructor(private httpClient: HttpClient) {
    this.currentUserLoggedIn = new BehaviorSubject<boolean>(sessionStorage.getItem('token') != null);
    this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem('token') || "");
  }

  login(credentials: LoginRequest): Observable<any> {
    return this.httpClient.post<any>(environment.urlHost + 'auth/login', credentials).pipe(
      tap((userData) => {
        sessionStorage.setItem('token', userData.token);
        sessionStorage.setItem('usuario_Id', userData.usuario_Id);
        this.currentUserData.next(userData.token);
        this.currentUserLoggedIn.next(true);
      }),
      map((userData) => userData.token),
      catchError(this.errorHandler)
    );
  }

  logout(): void {
    sessionStorage.removeItem('token');
    this.currentUserLoggedIn.next(false);
  }

  private errorHandler(error: HttpErrorResponse) {
    if (error.status === 0) {
      console.error('Se produjo un error ', error.error);
    } else {
      console.error('Error de la API: ', error);
    }
    return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
  }

  get userData(): Observable<String> {
    return this.currentUserData.asObservable();
  }

  get userLoggedIn(): Observable<boolean> {
    return this.currentUserLoggedIn.asObservable();
  }

  get userToken(): String {
    return this.currentUserData.value;
  }

}
