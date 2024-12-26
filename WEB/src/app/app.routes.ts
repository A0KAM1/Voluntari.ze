import { Routes } from "@angular/router";
import { ForgotMyPasswordComponent } from "./components/sign-in/forgot-my-password/forgot-my-password.component";
import { SignInComponent } from "./components/sign-in/sign-in.component";
import { RegisterNgoComponent } from "./components/sign-up/register-ngo/register-ngo.component";
import { RegisterUserComponent } from "./components/sign-up/register-user/register-user.component";
import { SignUpComponent } from "./components/sign-up/sign-up.component";
import { MySubscriptionsComponent } from "./components/user/my-subscriptions/my-subscriptions.component";
import { FeedComponent } from "./components/feed/feed.component";
import { MyProfileComponent } from "./components/ngo/my-profile/my-profile.component";
import { AbouUsComponent } from "./components/about-us/about-us.component";

const routeConfig: Routes = [
    {
        path: '',
        component: FeedComponent,
        title: 'Página Inicial'
    },
    {
        path: 'sign-up',
        component: SignUpComponent
    },
    {
        path: 'sign-in',
        component: SignInComponent
    },
    {
        path: 'forgot-my-password',
        component: ForgotMyPasswordComponent
    },
    {
        path: 'my-subscriptions',
        component: MySubscriptionsComponent
    },
    {
        path: 'sign-up-user',
        component: RegisterUserComponent
    },
    {
        path: 'sign-up-ong',
        component: RegisterNgoComponent
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
        component: AbouUsComponent
    }
];

export default routeConfig;
