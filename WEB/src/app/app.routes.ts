import { Routes } from '@angular/router';
import { ForgotMyPasswordComponent } from './pages/sign-in/forgot-my-password/forgot-my-password.component';
import { SignInComponent } from './pages/sign-in/sign-in.component';
import { RegisterNgoComponent } from './pages/sign-up/register-ngo/register-ngo.component';
import { RegisterUserComponent } from './pages/sign-up/register-user/register-user.component';
import { SignUpComponent } from './pages/sign-up/sign-up.component';
import { MySubscriptionsComponent } from './components/user/my-subscriptions/my-subscriptions.component';
import { FeedComponent } from './pages/feed/feed.component';
import { MyProfileComponent } from './components/ngo/my-profile/my-profile.component';
import { AbouUsComponent } from './pages/about-us/about-us.component';

const routeConfig: Routes = [
  {
    path: '',
    component: FeedComponent,
    title: 'Página Inicial',
  },
  {
    path: 'sign-up',
    component: SignUpComponent,
    title: 'Cadastro',
    children: [
      {
        path: 'ngo',
        component: RegisterNgoComponent,
        title: 'Cadastro de Ong',
      },
      {
        path: 'volunteer',
        component: RegisterUserComponent,
        title: 'Cadastro de Voluntário',
      },
    ],
  },
  {
    path: 'sign-in',
    component: SignInComponent,
    title: 'Sign In',
  },
  {
    path: 'forgot-my-password',
    component: ForgotMyPasswordComponent,
    title: 'Esqueci Minha Senha',
  },
  {
    path: 'my-subscriptions',
    component: MySubscriptionsComponent,
    title: 'Minhas Inscrições',
  },
  {
    path: 'my-profile',
    component: MyProfileComponent,
    // resolve: {
    //     user: () => inject(NgoService).getOngInfo(1).pipe(catchError(() => {
    //         return of(false)
    //     }))
    // }
  },
  {
    path: 'about-us',
    component: AbouUsComponent,
    title: 'Sobre Nós',
  },
];

export default routeConfig;
