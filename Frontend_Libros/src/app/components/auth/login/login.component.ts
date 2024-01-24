import {Component} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../../../services/auth/login.service";
import {LoginRequest} from "../../../interfaces/auth/login-request";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})

export class LoginComponent {
  loginError: string = "";
  loginForm = this.formBuilder.group({
    correo: ['', [Validators.required, Validators.email]],
    clave: ['', Validators.required]
  })

  constructor(private formBuilder: FormBuilder, private router: Router, private loginService: LoginService) {
  }

  get email() {
    return this.loginForm.controls.correo;
  }

  get password() {
    return this.loginForm.controls.clave;
  }

  login() {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value as LoginRequest).subscribe({
        next: (userData) => {
          const usuario_Id = sessionStorage.getItem('usuario_Id');
        },
        error: (error) => {
          console.log(error);
          this.loginError = error;
        },
        complete: () => {
          console.info("Login completo");
          this.router.navigateByUrl('/user');
          this.loginForm.reset();
        }
      });
    } else {
      this.loginForm.markAllAsTouched();
      alert("Formulario invalido");
    }
  }

  protected readonly require = require;
}
