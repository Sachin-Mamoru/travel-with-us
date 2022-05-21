import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';

import { MaterialModule } from './modules/utils/material/material.module';
import { DashboardModule } from './modules/dashboard/dashboard.module';
import { ContractModule } from './modules/contract/contract.module';
import { AppHomeComponent } from './app-home/app-home.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SearchModule } from './modules/search/search.module';
import { ViewModule } from './modules/view/view.module';
import {AboutUsModule} from "./modules/about-us/about-us.module";

@NgModule({
  declarations: [
    AppComponent,
    AppHomeComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    FlexLayoutModule,
    MaterialModule,
    SearchModule,
    AboutUsModule,
    DashboardModule,
    ViewModule.forRoot(),
    ContractModule.forRoot(),
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
