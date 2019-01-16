import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserIoComponent } from './user-io.component';

describe('UserIoComponent', () => {
  let component: UserIoComponent;
  let fixture: ComponentFixture<UserIoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserIoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserIoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
