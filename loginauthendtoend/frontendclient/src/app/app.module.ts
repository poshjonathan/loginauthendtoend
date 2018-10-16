import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from "@angular/router";

/*-----------Components-------------*/
import { AppComponent } from './app.component';
import { CxxxuserComponent } from './component/cxxxuser/cxxxuser.component';
import { RegisterComponent } from './component/cxxxuser/register/register.component';
import { LoginComponent } from './component/cxxxuser/login/login.component';
import { DashboardComponent } from './component/dashboard/dashboard.component';
import { HomeComponent } from './component/home/home.component';

/*-----------Services-------------*/
import {AppService} from "./app.service";
import {AuthenticationService} from "./service/authentication.service";
import {TokenStorage} from "./service/token.storage";
import {Token} from "@angular/compiler";
import {Interceptor} from "./app.interceptor";


const appRoutes: Routes =[
  {
    path: 'login',
    component: LoginComponent,
    data: { title: 'Please login' },
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: { title: 'Register User' },
  },
  {
    path: 'home',
    component: HomeComponent,
    data: { title: 'This is home page, no permission required' },
  },
  {
    path: 'dashboard/:name',
    component: DashboardComponent,
    data: { title: 'This is dashboard, you are lgged in' },
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    data: { title: 'dashboard' },
  },
  // {
  //   path: 'detail/:id',
  //   component: DetailComponent,
  //   data: { title: 'Contact Detail' }
  // },
  // {
  //   path: 'create',
  //   component: CreateComponent,
  //   data: { title: 'Create a new user' }
  // },
  // {
  //   path: 'edit/:id',
  //   component: EditComponent,
  //   data: { title: 'Edit a user' }
  // },
  { path: '',
    redirectTo: '/login',
    pathMatch: 'full'
  }
];


@NgModule({
  declarations: [
    AppComponent,
    CxxxuserComponent,
    RegisterComponent,
    LoginComponent,
    DashboardComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(
      appRoutes,
      { enableTracing: true, // <-- debugging purposes only
      }
    )
  ],
  providers: [AppService, AuthenticationService, TokenStorage, {
    provide:HTTP_INTERCEPTORS,
    useClass: Interceptor,
    multi :true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
