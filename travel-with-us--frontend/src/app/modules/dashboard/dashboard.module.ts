import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DashboardRoutingModule } from './dashboard-routing.module';
import { ButtonListComponent } from './button-list/button-list.component';
import { ImgSliderComponent } from './img-slider/img-slider.component';
import { DashboardComponent } from './dashboard.component';


@NgModule({
  declarations: [ButtonListComponent, ImgSliderComponent, DashboardComponent],
  imports: [
    CommonModule,
    DashboardRoutingModule
  ]
})
export class DashboardModule { }
