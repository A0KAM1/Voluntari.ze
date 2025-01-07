import { Component } from '@angular/core';
// import { IOngInfo } from '../models/ong-info';
// import { NgoService } from '../ngo.service';
import { CommonModule } from '@angular/common';
// import { MatSnackBar } from '@angular/material/snack-bar';
// import { ActivatedRoute, Router } from '@angular/router';
// import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-my-profile',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './my-profile.component.html',
})
export class MyProfileComponent {
  // isLoaded: boolean = false;
  // private _subscription: Subject<any> = new Subject<any>();
  // userInfo: IOngInfo;
  // constructor(
  //   private _service: NgoService,
  //   private _snackBar: MatSnackBar,
  //   private _route: ActivatedRoute,
  //   private _router: Router
  // ){}
  // ngOnInit(): void {
  // }
  // ngOnInit(): void {
  //   this._route.data.subscribe(res => {
  //     if(res.user == false){
  //       this._snackBar.open("The server is off.", "Close", { duration: 3000 });
  //       this._router.navigate(["/sign-in"]);
  //       return;
  //     }
  //     this.isLoaded = true;
  //   });
  //   this._service.ongInfo$
  //   .pipe(takeUntil(this._subscription))
  //   .subscribe(res => {
  //     this.userInfo = res;
  //   });
  // };
}
