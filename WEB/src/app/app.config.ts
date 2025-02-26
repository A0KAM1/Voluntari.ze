import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';

import routeConfig from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import { provideHttpClient, withFetch } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { providePrimeNG } from 'primeng/config';

import { provideStore } from '@ngrx/store';
import { sidebarReducer } from './store/sidebar/sidebar.reducer';
import ThemePreset from '../styles/themepreset';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routeConfig),
    provideClientHydration(),
    provideHttpClient(withFetch()),
    provideAnimationsAsync(),
    providePrimeNG({
      theme: {
        preset: ThemePreset,
        options: {
          darkModeSelector: '.dark-mode',
        },
      },
    }),
    provideStore({ sidebar: sidebarReducer }),
  ],
};
