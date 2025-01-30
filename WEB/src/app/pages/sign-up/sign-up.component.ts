import { Component } from '@angular/core';
import { TooltipModule } from 'primeng/tooltip';
import { RouterModule, RouterOutlet } from '@angular/router';
import { RegisterNgoComponent } from './register-ngo/register-ngo.component';
import { RegisterUserComponent } from './register-user/register-user.component';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    RouterModule,
    RegisterNgoComponent,
    RegisterUserComponent,
    TooltipModule,
    CardModule,
  ],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.scss',
})
export class SignUpComponent {}
