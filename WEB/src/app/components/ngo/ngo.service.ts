// import { HttpClient } from "@angular/common/http";
// import { Injectable } from "@angular/core";
// import { BehaviorSubject, Observable, tap } from "rxjs";
// import { IOngInfo } from "./models/ong-info";

// @Injectable({
//     providedIn: 'root'
// })
// export class NgoService{
//     readonly apiUrl = 'http://localhost:8080/ongs/1';
//     private _ongInfo: BehaviorSubject<IOngInfo | null> = new BehaviorSubject(null);

//     constructor(
//         private _httpClient: HttpClient
//     ){}

//     get ongInfo$(): Observable<IOngInfo>{
//         return this._ongInfo.asObservable();
//     }

//     getOngInfo(id: number): Observable<IOngInfo>{
//         return this._httpClient.get<IOngInfo>(this.apiUrl)
//         .pipe(tap(res => {
//             this._ongInfo.next(res);
//         }))
//     }
// }