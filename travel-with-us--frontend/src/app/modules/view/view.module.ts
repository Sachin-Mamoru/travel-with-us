import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViewRoutingModule } from './view-routing.module';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { ViewComponent } from './view.component';

import { MaterialModule } from '../utils/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ContractsComponent } from './contracts/contracts.component';
import { ViewService } from './service/view.service';
import { HotelComponent } from './hotel/hotel.component';

@NgModule({
  declarations: [ViewComponent, ContractsComponent, HotelComponent],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MaterialModule,
    HttpClientModule,
    FlexLayoutModule,
    CommonModule,
    ViewRoutingModule,
  ],
})
export class ViewModule {
  static forRoot(): ModuleWithProviders<ViewModule> {
    return {
      ngModule: ViewModule,
      providers: [ViewService],
    };
  }
}
