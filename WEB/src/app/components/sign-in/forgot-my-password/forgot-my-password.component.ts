import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';

@Component({
  selector: 'app-forgot-my-password',
  standalone: true,
  imports: [
    CardModule,
    InputTextModule,
    ButtonModule,
    RouterModule
  ],
  templateUrl: './forgot-my-password.component.html',
  styleUrl: './forgot-my-password.component.scss'
})
export class ForgotMyPasswordComponent {

}
