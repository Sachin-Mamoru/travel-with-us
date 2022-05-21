import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ImgSliderComponent } from './img-slider.component';
import {AppModule} from '../../../app.module'

describe('ImgSliderComponent', () => {
  let component: ImgSliderComponent;
  let fixture: ComponentFixture<ImgSliderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ImgSliderComponent ],
      imports: [AppModule]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ImgSliderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
