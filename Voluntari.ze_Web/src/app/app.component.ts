import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LoginComponent } from './sign-in/login/login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    SignUpComponent,
    LoginComponent,
    RouterModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'Voluntari.ze_Web';
}
