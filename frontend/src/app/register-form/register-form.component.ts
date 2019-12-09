import { Component, OnInit } from "@angular/core";
import { AuthService } from "../auth.service";
import { Validators, FormBuilder } from "@angular/forms";
import { Router } from "@angular/router";
import { Course } from '../course';
import { User } from '../user';

@Component({
  selector: "app-register-form",
  templateUrl: "./register-form.component.html",
  styleUrls: ["./register-form.component.css"]
})
export class RegisterFormComponent implements OnInit {
  message: string;
  hidePassword = true;

  form = this.fb.group({
    neptunCode: ["", [Validators.required]],
    name: ["", [Validators.required]],
    password: ["", [Validators.required]]
  });

  get neptunCode() {
    return this.form.get("neptunCode");
  }
  get name() {
    return this.form.get("name");
  }
  get password() {
    return this.form.get("password");
  }
 

  constructor(
    private authService: AuthService,
    private router: Router,
    private fb: FormBuilder,
  ) {}

  async ngOnInit(): Promise<void> {
  }
  
  async onSubmit() {
    try {
      this.message = null;
      await this.authService.signin(
        this.neptunCode.value,
        this.name.value,
        this.password.value
      );
      if (this.authService.redirectUrl) {
        this.router.navigate(["/login"]);
      } else {
        this.router.navigate(["/login"]);
      }
    } catch (e) {
      console.log("d")
      this.message = "Cannot Register!";
    }
  }
  

}
