import { Component } from "@angular/core";
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { FloatLabelModule } from 'primeng/floatlabel';
import { CardModule } from 'primeng/card';
import { DividerModule } from 'primeng/divider';
import { FileUploadModule } from 'primeng/fileupload';


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
    ],
    templateUrl: './sign-up.component.html',
    styleUrl: './sign-up.component.scss'
})
export class SignUpComponent{
    value: string | undefined;
    onUpload() {
        
    }
}