import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginComponent } from './login.component';
import {RouterTestingModule} from '@angular/router/testing';
import {FormsModule} from '@angular/forms';
import {DebugElement} from '@angular/core';
import {AuthenticationService} from '../../../service/authentication.service';
import {HttpClientModule} from '@angular/common/http';
import {TokenStorage} from '../../../service/token.storage';
import {By} from '@angular/platform-browser';
import {log} from "util";
import {logging} from "selenium-webdriver";
import {logger} from "codelyzer/util/logger";

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let debugElement: DebugElement;
  let loginBtn: HTMLButtonElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule, FormsModule, HttpClientModule],
      declarations: [ LoginComponent ],
      providers: [AuthenticationService, TokenStorage]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    // Fixtures have access to a debugElement,
    // which will give you access to the internals of the component fixture.
    debugElement = fixture.debugElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  /*-------Check if title is Login Page-------*/
  // async : We use an async before each.
  // The purpose of the async is to let all the possible asynchronous code to finish before continuing.
  it('should have title as Login Page', async(() => {
    const app = debugElement.componentInstance;
    expect(app.title).toEqual('Login Page');
  }));

  /*-------Check if h2 of the Login Page is: Please enter your credentials-------*/
  it('should have title as: Please enter your credentials', async(() => {
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h2').textContent).toContain('Please enter your credentials');
  }));

  /*-------Check if Login Button exist-------*/

  /*-------Check if the button is disabled when the textArea is empty-------*/
  it('should disable the button when textArea is empty', async(() => {

    loginBtn = fixture.debugElement.query(By.css('.loginButton')).nativeElement;

    // You must tell the TestBed to perform data
    // binding by calling fixture.detectChanges()
    // only then does the button have the expected status
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(loginBtn.disabled).toBe(true);
    });
  }));

  // it('should enable button when textArea is not empty', () => {
  //   component.model.name = 'I love this test';
  //   fixture.detectChanges();
  //   const button = fixture.debugElement.query(By.css('buttocn'));
  //   expect(button.nativeElement.disabled).toBeFalsy();
  // });



});
