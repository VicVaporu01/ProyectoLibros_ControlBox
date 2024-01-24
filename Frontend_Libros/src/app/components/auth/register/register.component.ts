import {Component} from '@angular/core';
import {FormBuilder, ReactiveFormsModule, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {User} from "../../../interfaces/auth/user";
import {RegisterService} from "../../../services/auth/register.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  registerForm = this.formBuilder.group({
    correo: ['', [Validators.required, Validators.email]],
    clave: ['', [Validators.required, Validators.minLength(5)]],
    nombre_Usuario: ['', [Validators.required, Validators.minLength(5)]]
  });

  newUser: User;

  constructor(private formBuilder: FormBuilder, private _registerService: RegisterService, private router: Router) {
    this.newUser = {} as User;
  }

  register() {
    if (this.registerForm.valid) {
      this.newUser = this.registerForm.value as User;
      this._registerService.register(this.newUser).subscribe({
        next: (newUser: User) => {
          console.log(newUser.nombre_Usuario + " registrado correctamente");
        },
        error: (error) => {
          console.log(error);
        },
        complete: () => {
          console.info("Registro completo");
          this.registerForm.reset();
          this.router.navigateByUrl('/login').then(r => console.log(r));
        }
      });
    }
  }

  get email() {
    return this.registerForm.controls.correo;
  }

  get password() {
    return this.registerForm.controls.clave;
  }

  get username() {
    return this.registerForm.controls.nombre_Usuario;
  }

}
