import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FileUploadModule } from 'primeng/fileupload';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CommonModule } from "@angular/common";


@Component({
  selector: 'register-user',
  standalone: true,
  imports: [
    ButtonModule,
    DropdownModule,
    FloatLabelModule,
    CardModule,
    DividerModule,
    FileUploadModule, 
    SelectButtonModule,
    CommonModule,
    
  ],
  templateUrl: './register-user.component.html',
  styleUrl: './register-user.component.scss'
})
export class RegisterUserComponent {
    value: string | undefined;

    stateOptions: any[] = [
      { 
          label: 'Cadastro de Perfil', 
          // value: 'registerUser',
          value: "routerLink='/register-user'"

      },
      { 
          label: 'Cadastro de ONG', 
          value: './register-user-component.html' 
      }
  ];
  onUpload(){
    
  }
}
