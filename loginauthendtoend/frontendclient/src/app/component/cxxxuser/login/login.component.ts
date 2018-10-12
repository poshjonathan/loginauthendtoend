import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppService} from "../../../app.service";
import {Observable} from "rxjs";
import {AuthenticationService} from "../../../service/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

model: any = {};
  loading = false;
  error = '';

  constructor(private route: ActivatedRoute,
              private router: Router,
              private http: HttpClient,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    //sessionStorage.setItem('token','');
    // reset login status
    this.authenticationService.logout();
    console.log("Login Init");
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.name, this.model.password)
      .subscribe(result => {
        if (result === true) {
          // login successful
          this.router.navigate(['dashboard']);
        } else {
          // login failed
          this.error = 'Username or password is incorrect';
          this.loading = false;
        }
      }, error => {
        this.loading = false;
        this.error = error;
      });
  }
  // login() {
  //   let url = 'http://localhost:8080/login';
  //   this.http.post<Observable<boolean>>(url, {
  //     name: this.model.name,
  //     password: this.model.password
  //   }).subscribe(isValid => {
  //     if (isValid) {
  //       sessionStorage.setItem(
  //         'token',
  //         btoa(this.model.name + ':' + this.model.password)
  //       );
  //       this.router.navigate(['/dashboard',this.model.name]);
  //     } else {
  //       alert("Authentication failed.")
  //     }
  //   });
  //
  // }

  // login() {
  //   let url = 'http://localhost:8080/login';
  //   this.http.post<Observable<boolean>>(url, {
  //     name: this.model.name,
  //     password: this.model.password
  //   })
  //
  // }


  // login() {
  //   const url = 'http://localhost:8080/login';
  //   const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(this.model.name+':'+this.model.password) });
  //   return this.http.post(url, { headers });
  //
  //   this.router.navigate(['/dashboard',this.model.name]);
  // }

}
