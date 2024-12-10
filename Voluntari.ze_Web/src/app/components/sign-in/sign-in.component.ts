import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { DialogModule } from 'primeng/dialog';
import { ImageModule } from 'primeng/image';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';

@Component({
  selector: 'app-sign-in',
  standalone: true,
  imports: [
    CardModule,
    InputTextModule,
    ButtonModule,
    DialogModule,
    RouterModule,
    ImageModule,
    PasswordModule
  ],
  templateUrl: './sign-in.component.html',
  styleUrl: './sign-in.component.scss'
})
export class SignInComponent {
  makeDialogVisible: boolean = false;

  constructor() { }

  showDialog(): void {
    this.makeDialogVisible = true;
  }
}
