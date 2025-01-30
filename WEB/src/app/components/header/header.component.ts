import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
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
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  checked = true;
  isOpen$: Observable<boolean> = of(false);
  theme: 'dark' | 'light' = 'light';

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.isOpen$ = this.store.select(selectIsOpen);
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
