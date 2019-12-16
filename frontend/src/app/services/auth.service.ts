import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  static readonly USER_TOKEN_KEY = 'user_token';
  static readonly SECURITY_REGISTER_ENDPOINT = `${environment.register_endpoint}`;
  static readonly SECURITY_LOGIN_ENDPOINT = `${environment.login_endpoint}`;

  constructor(public http: HttpClient) {
    console.log('Hello AuthService Provider');
  }

  static handleLoginSuccess(data) {
    AuthService.storeToken(data.token);
  }

  static storeToken(token: string): void {
    localStorage.setItem(AuthService.USER_TOKEN_KEY, token);
  }

  static getToken() {
    return localStorage.getItem(AuthService.USER_TOKEN_KEY);
  }

  static logout() {
    localStorage.removeItem(AuthService.USER_TOKEN_KEY);
  }

  register(user: any) {
    return this.http
        .post(`${AuthService.SECURITY_REGISTER_ENDPOINT}`, user);
  }

  login(user: any) {
    return this.http
        .post(`${AuthService.SECURITY_LOGIN_ENDPOINT}`, user);
  }
}
