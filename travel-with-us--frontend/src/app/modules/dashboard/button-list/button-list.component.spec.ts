import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ButtonListComponent } from './button-list.component';
import {AppModule} from '../../../app.module'

describe('ButtonListComponent', () => {
  let component: ButtonListComponent;
  let fixture: ComponentFixture<ButtonListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ButtonListComponent ],
      imports: [AppModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ButtonListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
