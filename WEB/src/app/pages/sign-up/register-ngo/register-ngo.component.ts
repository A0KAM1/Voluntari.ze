import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { FileUploadModule } from 'primeng/fileupload';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { InputNumber } from 'primeng/inputnumber';
import { TextareaModule } from 'primeng/textarea';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-register-ngo',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ButtonModule,
    InputTextModule,
    FileUploadModule,
    MatIconModule,
    PasswordModule,
    InputNumber,
    TextareaModule,
  ],
  templateUrl: './register-ngo.component.html',
  styleUrl: './register-ngo.component.scss',
})
export class RegisterNgoComponent {
  // choose(event?: any, callback?: any) {
  //   callback();
  // }
}
