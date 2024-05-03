import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ForgotMyPasswordComponent } from './sign-in/forgot-my-password/forgot-my-password.component';
import { RegisterUserComponent } from './sign-up/register-user/register-user.component';
import { RegisterNgoComponent } from './sign-up/register-ngo/register-ngo.component';


const routeConfig: Routes = [
    {
        path: '',
        component: AppComponent,
        title: 'Pagina inicial'
    },
    {
        path: 'sign-up',
        component: SignUpComponent,
        children: [
            {
                path: 'register-user',
                component: RegisterUserComponent
            },
            {
                path: 'register-ngo',
                component: RegisterNgoComponent
            }
        ]
    },
    { 
        path: 'forgot-my-password',
        component: ForgotMyPasswordComponent
    },
    {
        path: 'register-user',
        component: RegisterUserComponent
    }
    
];

export default routeConfig;