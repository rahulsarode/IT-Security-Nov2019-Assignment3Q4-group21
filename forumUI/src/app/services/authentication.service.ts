import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { map } from 'rxjs/operators';
import { BehaviorSubject, Observable } from 'rxjs';

import { User } from '../models/user.model';
import { AlertService } from '../services/alert.service';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<any>;
  public currentUser: Observable<any>;
  constructor(
    private http: HttpClient,
    private router: Router) {
    this.currentUserSubject = new BehaviorSubject<any>(JSON.parse(localStorage.getItem('currentUser')));
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): any {
    return this.currentUserSubject.value;
  }

  login(user: User) {
    return this.http.post<any>('//localhost:8080/login', user, httpOptions)
      .pipe(map(user => {
        if (user) {
          this.currentUserSubject.next(user);
        }
        return user;
      }));
  }

  logout() {
    this.currentUserSubject.next(null);
    localStorage.clear();
    this.router.navigate(['/login']);
  }
}
