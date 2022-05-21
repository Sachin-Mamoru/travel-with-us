import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppHomeComponent} from './app-home/app-home.component';
import {DashboardComponent} from './modules/dashboard/dashboard.component';
import {ContractComponent} from './modules/contract/contract.component';
import {SearchComponent} from './modules/search/search.component';
import {ViewComponent} from './modules/view/view.component';
import {AboutUsComponent} from "./modules/about-us/about-us.component";

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {
    path: 'home', component: AppHomeComponent, children: [
      {path: '', component: DashboardComponent},
      {path: 'contract', component: ContractComponent},
      {path: 'search', component: SearchComponent},
      {path: 'view', component: ViewComponent},
      {path: 'about', component: AboutUsComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
