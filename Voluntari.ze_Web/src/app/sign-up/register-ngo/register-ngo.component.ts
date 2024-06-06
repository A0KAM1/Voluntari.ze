import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FileUploadModule } from 'primeng/fileupload';
import { InputTextModule } from "primeng/inputtext";
import { DialogModule } from "primeng/dialog";
import { RouterModule } from "@angular/router";
import { ImageModule } from "primeng/image";
import { PasswordModule } from "primeng/password";

@Component({
  selector: 'app-register-ngo',
  standalone: true,
  imports: [
    ButtonModule,
    DropdownModule,
    InputTextModule,
    FloatLabelModule,
    CardModule,
    DividerModule,
    FileUploadModule,
    ButtonModule,
    DialogModule,
    RouterModule,
    ImageModule,
    PasswordModule
  ],
  templateUrl: './register-ngo.component.html',
  styleUrl: './register-ngo.component.scss'
})
export class RegisterNgoComponent {

}
