import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FileUploadModule } from 'primeng/fileupload';
import { SelectButtonModule } from 'primeng/selectbutton';
import { RouterModule, RouterOutlet } from "@angular/router";
import { RegisterUserComponent } from "./register-user/register-user.component";
import { RegisterNgoComponent } from "./register-ngo/register-ngo.component";


@Component({
    selector: 'sign-up',
    standalone: true,
    imports: [
        ButtonModule,
        DropdownModule,
        FloatLabelModule,
        CardModule,
        DividerModule,
        FileUploadModule, 
        SelectButtonModule,
        RouterOutlet,
        RegisterUserComponent,
        RouterModule,
        RegisterNgoComponent
    ],
    templateUrl: './sign-up.component.html',
    styleUrl: './sign-up.component.scss'
})
export class SignUpComponent{
    value: string | undefined;
    // onUpload() {
        
    // }
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
}
