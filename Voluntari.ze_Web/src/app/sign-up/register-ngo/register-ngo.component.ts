import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FileUploadModule } from 'primeng/fileupload';
import { SelectButtonModule } from 'primeng/selectbutton';
import { CommonModule } from "@angular/common";
import { MultiSelectModule } from 'primeng/multiselect';
import { RouterOutlet } from "@angular/router";

@Component({
  selector: 'app-register-ngo',
  standalone: true,
  imports: [
    MultiSelectModule,
    ButtonModule,
    DropdownModule,
    FloatLabelModule,
    CardModule,
    DividerModule,
    FileUploadModule, 
    SelectButtonModule,
    CommonModule,
    RouterOutlet
  ],
  templateUrl: './register-ngo.component.html',
  styleUrl: './register-ngo.component.scss'
})
export class RegisterNgoComponent {
  onUpload(){
    
  }
}
