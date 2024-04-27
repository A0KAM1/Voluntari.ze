import { Routes } from "@angular/router";
import { AppComponent } from "./app.component";
import { SignUpComponent } from "./sign-up/sign-up.component";
import { ForgotMyPasswordComponent } from "./sign-in/forgot-my-password/forgot-my-password.component";
import { LoginComponent } from "./sign-in/login/login.component";

const routeConfig: Routes = [
    {
        path: '',
        component: AppComponent,
        title: 'Página Inicial'
    },
    {
        path: 'sign-up',
        component: SignUpComponent
    },
    {
        path: 'sign-in',
        component: LoginComponent
    },
    {
        path: 'forgot-my-password',
        component: ForgotMyPasswordComponent
    }
];

export default routeConfig;