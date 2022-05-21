import { Component, OnInit, EventEmitter } from '@angular/core';
import { ViewService } from '../service/view.service';

@Component({
  selector: 'app-contracts',
  templateUrl: './contracts.component.html',
  styleUrls: ['./contracts.component.scss']
})
export class ContractsComponent implements OnInit {

  public contracts = [];
  public isLoadContracts = false;

  constructor(private viewService: ViewService) { }

  ngOnInit(): void {
    this.searchContracts('');
  }

  //contract searching key up event
  onKey(event: any) {
    this.searchContracts(event.target.value);
  }

  //search request
  searchContracts(searchKey: string){
    this.isLoadContracts = false;
    this.viewService
      .searchContracts(
        searchKey
      )
      .subscribe(
        (response: any) => {
          this.isLoadContracts = true;
          this.contracts = response;
        },
        (error) => {
          window.alert('Search error: ' + error);
        },
        () => {
        }
      );
  }

}
