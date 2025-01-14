import { MatIconModule } from '@angular/material/icon';
import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { toggleSidebar } from 'app/store/sidebar/sidebar.actions';
import { Observable, of } from 'rxjs';
import { selectIsOpen } from 'app/store/sidebar/sidebar.selectors';
import { CommonModule } from '@angular/common';
import { Button } from 'primeng/button';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule, RouterModule, MatIconModule, FormsModule, Button],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss',
})
export class HeaderComponent {
  checked = true;
  isOpen$: Observable<boolean> = of(false);

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
  }
}
