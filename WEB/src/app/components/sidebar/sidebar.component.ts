import { Observable, of } from 'rxjs';
import { CommonModule } from "@angular/common";
import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { Store } from "@ngrx/store";
import { closeSideBar } from "app/store/sidebar/sidebar.actions";
import { selectIsOpen } from "app/store/sidebar/sidebar.selectors";

@Component({
  selector: 'sidebar',
  standalone: true,
  imports: [ CommonModule, RouterModule ],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.scss'
})
export class SidebarComponent implements OnInit {
  isOpen$: Observable<boolean> = of(false);

  constructor(private store: Store){}

  ngOnInit(): void {
    this.isOpen$ = this.store.select(selectIsOpen);
  }

  handleSidebar(){
    this.store.dispatch(closeSideBar())
  }
}
