import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-button-list',
  templateUrl: './button-list.component.html',
  styleUrls: ['./button-list.component.scss']
})
export class ButtonListComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  //route to contract form
  onAddContract(){
    this.router.navigate(['/home/contract']);
  }

  //route to search
  onSearch(){
    this.router.navigate(['/home/search']);
  }

  //route to view existing hotels and contracts
  onView(){
    this.router.navigate(['/home/view']);
  }

}
