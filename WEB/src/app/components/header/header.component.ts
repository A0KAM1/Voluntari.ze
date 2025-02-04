import { Component } from '@angular/core';
import { ActivationEnd, Event, Router, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';

import { Store } from '@ngrx/store';
import { Observable, of } from 'rxjs';
import { FormsModule } from '@angular/forms';
import { toggleSidebar } from 'app/store/sidebar/sidebar.actions';
import { selectIsOpen } from 'app/store/sidebar/sidebar.selectors';
import { Button } from 'primeng/button';
import { InputText } from 'primeng/inputtext';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { LogoComponent } from '../logo/logo.component';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    FormsModule,
    Button,
    InputText,
    IconFieldModule,
    InputIconModule,
    LogoComponent,
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  checked = true;
  isOpen$: Observable<boolean> = of(false);
  theme: 'dark' | 'light' = 'light';
  pageTitle: string = '';

  constructor(
    private store: Store,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.isOpen$ = this.store.select(selectIsOpen);

    this.router.events.subscribe((event: Event) => {
      if (event instanceof ActivationEnd) {
        this.pageTitle =
          event.snapshot.routeConfig?.title?.toString() || 'Pagina Inicial';
      }
    });
  }

  handleSidebar() {
    this.store.dispatch(toggleSidebar());
  }

  toggleDarkMode() {
    const element = document.querySelector('html');
    element?.classList.toggle('dark-mode');
    this.theme = this.theme === 'dark' ? 'light' : 'dark';
  }
}
