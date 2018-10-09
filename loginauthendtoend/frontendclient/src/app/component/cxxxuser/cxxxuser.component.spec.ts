import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CxxxuserComponent } from './cxxxuser.component';

describe('CxxxuserComponent', () => {
  let component: CxxxuserComponent;
  let fixture: ComponentFixture<CxxxuserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CxxxuserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CxxxuserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
