import { MatIconModule } from '@angular/material/icon';
import { Component } from "@angular/core";
import { RouterModule } from "@angular/router";

import { ToggleSwitch } from 'primeng/toggleswitch';
import { FormsModule } from '@angular/forms';
import { Store } from '@ngrx/store';
import { toggleSidebar } from 'app/store/sidebar/sidebar.actions';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [
    RouterModule,
    MatIconModule,
    FormsModule,
    ToggleSwitch

   ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  checked = true;

  constructor(private store: Store){}

  handleSidebar() {
    this.store.dispatch(toggleSidebar());
  }
}
