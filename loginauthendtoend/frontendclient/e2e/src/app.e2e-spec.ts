import { AppPage } from './app.po';
import { browser, by, element } from 'protractor';
import {log} from "util";

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    // initialise to get page object from app.po.ts
    page = new AppPage();
  });

  it('should display welcome message', () => {
    // go to the default route
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to frontendclient!');
  });

  it('should display register button', () => {
    page.navigateTo();
    // page.getRegistrationButton().click();
    // expect(page.getRegistrationText()).toEqual('Add New Contact');
    expect(page.getRegistrationButton().getText()).toEqual('register');
  });

  it('should route to register page', () => {
    page.navigateTo();
     page.getRegistrationButton().click();
     expect(page.getRegistrationText()).toEqual('Add New Contact');
  });

});
