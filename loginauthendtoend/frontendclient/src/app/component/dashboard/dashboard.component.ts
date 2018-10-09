import { Component, OnInit } from '@angular/core';
import {AppService} from "../../app.service";
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  title = 'logged in ! ';
  cxuser : any;

  constructor(private route: ActivatedRoute, private http: HttpClient,private router: Router) {

  }


  // authenticated() {
  //   return this.app.authenticated;
  // }

  ngOnInit() {

    this.getCxuserDetail(this.route.snapshot.params['name']);
  }

  getCxuserDetail(name){

    this.http.get('http://localhost:8080/dashboard/'+name).subscribe(data => {
      this.cxuser = data;
    });
  }

}
