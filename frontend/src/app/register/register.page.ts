import { Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {NavController} from '@ionic/angular';
import {AuthService} from '../services/auth.service';
import {ToastService} from '../services/toast.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage {
  public registerForm: FormGroup;

  constructor(public navController: NavController, private formBuilder: FormBuilder, private authService: AuthService,
              private toastService: ToastService) {
    this.registerForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  onRegister() {
    if (this.registerForm.valid) {
      const userDetails = this.registerForm.value;
      this.authService.register(userDetails).subscribe(
          () => this.proceedToLogin(userDetails),
          () => {
            const message = 'This username is already taken.';
            this.toastService.showToast(message, 'bottom', 3000);
          });
    } else {
      const message = 'Please complete the username and password.';
      this.toastService.showToast(message, 'bottom', 3000);
    }
  }

  proceedToLogin(userDetails) {
    this.authService.login(userDetails).subscribe(
        (data: any) => {
          AuthService.handleLoginSuccess(data);
          this.navController.pop().then(
              () => this.navController.navigateRoot('/home')
          );
        },
        () => {
          this.navController.pop().then(
              () => this.navController.navigateRoot('/login')
          );
        });
  }
}
