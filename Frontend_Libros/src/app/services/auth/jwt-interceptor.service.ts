import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs";
import {LoginService} from "./login.service";

@Injectable({
  providedIn: 'root'
})
export class JwtInterceptorService implements HttpInterceptor {

  constructor(private _loginService: LoginService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    let token: String = this._loginService.userToken;

    if (token != "") {
      req = req.clone({
        setHeaders: {
          'Content-Type': 'application/json;charset=UTF-8',
          'Accept': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });
    }
    return next.handle(req);
  }
}
