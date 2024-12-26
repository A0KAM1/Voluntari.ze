import { Component } from '@angular/core';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { IconFieldModule } from 'primeng/iconfield';
import { InputIconModule } from 'primeng/inputicon';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-my-subscriptions',
  standalone: true,
  imports: [
    SidebarModule,
    ButtonModule,
    IconFieldModule,
    InputIconModule,
    TableModule
  ],
  templateUrl: './my-subscriptions.component.html',
  styleUrl: './my-subscriptions.component.scss'
})
export class MySubscriptionsComponent {
  sidebarVisible: boolean = false;
}
