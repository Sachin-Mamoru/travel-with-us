import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ViewComponent } from './view.component';
import { ContractsComponent } from './contracts/contracts.component';


const routes: Routes = [
  {path: 'view', component: ViewComponent, children: [
    {path: 'contract', component: ContractsComponent}
  ]}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ViewRoutingModule { }
