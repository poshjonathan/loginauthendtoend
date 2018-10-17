import { browser, by, element } from 'protractor';

export class AppPage {
  navigateTo() {
    return browser.get('/');
  }

  getParagraphText() {
    return element(by.css('app-root h1')).getText();
  }

  getRegistrationButton() {
    //const foo = element.all(by.css('.btn btn-default btn-lg'));
    //return (foo.getText());
     return element(by.css('[ng-reflect-router-link="/register"]'));
    // return element(by.css('.btn btn-default btn-lg')).element(by.css('.glyphicon glyphicon-plus'));

  }

  getRegistrationText() {
    return element(by.css('.form-title')).getText();
  }
}
