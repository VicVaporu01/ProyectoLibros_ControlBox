import {Injectable} from '@angular/core';
import {User} from "../../interfaces/auth/user";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpClient: HttpClient) {
  }

  register(newUser: User): Observable<User> {
    return this.httpClient.post<User>(environment.urlHost + "auth/register", newUser);
  }

}
