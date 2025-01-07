import { Component } from '@angular/core';
import { TooltipModule } from 'primeng/tooltip';
import { RouterModule, RouterOutlet } from '@angular/router';
import { RegisterNgoComponent } from './register-ngo/register-ngo.component';
import { RegisterUserComponent } from './register-user/register-user.component';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [
    RouterOutlet,
    RouterModule,
    RegisterNgoComponent,
    RegisterUserComponent,
    TooltipModule,
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
})
export class SignUpComponent {}
