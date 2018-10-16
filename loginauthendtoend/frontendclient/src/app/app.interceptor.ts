import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest, HttpUserEvent} from "@angular/common/http";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {TokenStorage} from "./service/token.storage";


const TOKEN_HEADER_KEY = 'Authorization';



/*




 */

@Injectable()
export class Interceptor implements HttpInterceptor {

  constructor(private token: TokenStorage, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler):
    Observable<HttpUserEvent<any>> {
    let authReq = req;
    if (this.token.getToken() != null) {
      authReq = req.clone({headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + this.token.getToken())});
    }

// Design for rxjs 6 / angular 6
    // stackoverflow: Angular 4 Http Interceptro: next.handle(...).do is not a function
    return next.handle(authReq).pipe(
      tap((err: any) => {
        if (err instanceof HttpErrorResponse) {

          if (err.status === 401) {
            //this.router.navigate(['user']);
          }
        }
      })
    );

// This is for Angular 5, need a pipe function in Angular 6
// return next.handle(authReq).do(
//   (err: any) => {
//     if (err instanceof HttpErrorResponse) {
//
//       if (err.status === 401) {
//         this.router.navigate(['user']);
//       }
//     }
//   }
// );
  }

}
