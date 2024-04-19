import { Routes } from '@angular/router';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ForgotMyPasswordComponent } from './sign-in/forgot-my-password/forgot-my-password.component';

export const routes: Routes = [
    {
        path: 'localhost:4200/sign-up',
        component: SignUpComponent
    },
    { 
        path: 'forgot-my-password',
        component: ForgotMyPasswordComponent
    }
];
