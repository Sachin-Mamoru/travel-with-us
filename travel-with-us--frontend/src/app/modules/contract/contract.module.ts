import { NgModule, ModuleWithProviders } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { ContractRoutingModule } from './contract-routing.module';
import { ContractComponent } from './contract.component';
import { FormComponent } from './form/form.component';
import { MaterialModule } from '../utils/material/material.module';
import { HttpClientModule } from '@angular/common/http';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ContractService } from './service/contract.service';

@NgModule({
  declarations: [ContractComponent, FormComponent],
  imports: [
    CommonModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    FlexLayoutModule,
    ContractRoutingModule,
  ],
})
export class ContractModule {
  static forRoot(): ModuleWithProviders<ContractModule> {
    return {
      ngModule: ContractModule,
      providers: [ContractService],
    };
  }
}
