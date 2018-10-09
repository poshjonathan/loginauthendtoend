// import { Injectable } from '@angular/core';
// import {HttpClient, HttpHeaders} from "@angular/common/http";
// import {PlatformLocation} from "@angular/common";
//
// @Injectable({
//   providedIn: 'root'
// })
// export class CxUserServiceService {
//
//   private _reqOptionsArgs= { headers: new HttpHeaders().set( 'Content-Type', 'application/json' ) };
//   private _utils = new Utils();
//   private myUserUrl = "/myuser";
//
//
//   constructor(private http: HttpClient, private pl: PlatformLocation) { }
//
//   postLogin(user: MyUser): Observable<MyUser> {
//     return this.http.post<MyUser>(this.myUserUrl+'/login', user, this._reqOptionsArgs).pipe(map(res => {
//       let retval = <MyUser>res;
//       localStorage.setItem("salt", retval.salt);
//       localStorage.setItem("token", retval.token);
//       return retval;
//     }),catchError(this._utils.handleError<MyUser>('postLogin')));
//
//
//
//   }
