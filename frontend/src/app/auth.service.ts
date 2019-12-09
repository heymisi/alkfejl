import { Injectable } from "@angular/core";
import { User } from "./user";
import { HttpHeaders, HttpClient } from "@angular/common/http";

export const httpOptions = {
  headers: new HttpHeaders({
    "Content-Type": "application/json",
    Authorization: ""
  })
};

@Injectable({
  providedIn: "root"
})
export class AuthService {
  public isLoggedIn: boolean = false;
  public user: User;
  public redirectUrl: string;
  private authUrl: string = "http://localhost:8080/users";

  constructor(private http: HttpClient) {
  }

  async signin(neptunCode, name, password) {
    try {
      const user = await this.http
        .post<User>(`${this.authUrl}/register`,{neptunCode,name,password},httpOptions)
        .toPromise();
      this.user = user;
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }

  async login(neptuncode: string, password: string): Promise<User> {
    try {
      const token = btoa(`${neptuncode}:${password}`);
      httpOptions.headers = httpOptions.headers.set(
        "Authorization",
        `Basic ${token}`
      );
      const user = await this.http
        .post<User>(`${this.authUrl}/login`, {}, httpOptions)
        .toPromise();
      this.isLoggedIn = true;
      this.user = user;
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
    }
  }

  logout() {
    httpOptions.headers = httpOptions.headers.set("Authorization", ``);
    this.isLoggedIn = false;
    this.user = null;
  }

  loggedInUser() : User{
    return this.user;
  }
}
