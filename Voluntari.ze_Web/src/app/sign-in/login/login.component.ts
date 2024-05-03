import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { RouterModule } from '@angular/router';
import { ImageModule } from 'primeng/image';
import { PasswordModule } from 'primeng/password';

@Component({
  selector: 'app-login',
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
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  makeDialogVisible: boolean = false;

  constructor() { }

  showDialog(): void {
    this.makeDialogVisible = true;
  }
}
