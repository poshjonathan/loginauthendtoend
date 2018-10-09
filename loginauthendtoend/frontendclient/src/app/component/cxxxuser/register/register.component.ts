import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  constructor(private http: HttpClient, private router: Router) { }
cxxxuser={};
  ngOnInit() {
  }
  saveCxxxUser() {
    this.http.post('http://localhost:8080/allusers', this.cxxxuser)
      .subscribe(res => {
         // let id = res['id'];
         // this.router.navigate(['/detail', id]);
        this.router.navigate(['/login']);
        }, (err) => {
          console.log(err);
        }
      );
  }

}
