import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http';
import {User} from './model/User';

@Injectable( { providedIn: 'root'  })
export class UserService {

  constructor(private http: HttpClient) { }

  getUser(id: string ): Observable<User> {
    console.log(id);
    return this.http.get<User>("http://localhost:8084/user/"+parseInt(id));
  }
  getAll(): Observable<any> {
      return this.http.get("http://localhost:8084/users/");
  }
  update(id: string, user :User): Observable<any> {
        return this.http.post("http://localhost:8084/user/"+id, user);
   }
}
