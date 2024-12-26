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
    selector: 'sign-up',
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
    templateUrl: './sign-up.component.html',
    styleUrl: './sign-up.component.scss'
})
export class SignUpComponent{
    value: string | undefined;
    showDialog() {
        
    }
}