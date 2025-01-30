import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Dialog } from 'primeng/dialog';
import { NewPostComponent } from './new-post/new-post.component';
import { ButtonModule } from 'primeng/button';

@Component({
  selector: 'app-feed',
  standalone: true,
  imports: [MatIconModule, Dialog, NewPostComponent, ButtonModule],
  templateUrl: './feed.component.html',
  styleUrl: './feed.component.scss',
})
export class FeedComponent {
  visible = false;

  closeDialog() {
    this.visible = false;
  }

  showDialog() {
    this.visible = true;
  }
}
