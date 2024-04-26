import { Routes, RouterModule } from '@angular/router';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ForgotMyPasswordComponent } from './sign-in/forgot-my-password/forgot-my-password.component';


const routeConfig: Routes = [
    {
        path: '',
        component: AppComponent,
        title: 'Pagina inicial'
    },
    {
        path: 'sign-up',
        component: SignUpComponent
    },
    { 
        path: 'forgot-my-password',
        component: ForgotMyPasswordComponent
    }
];

export default routeConfig;