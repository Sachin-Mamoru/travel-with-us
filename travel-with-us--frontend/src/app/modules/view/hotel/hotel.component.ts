import { Component, OnInit } from '@angular/core';
import { ViewService } from '../service/view.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.scss']
})
export class HotelComponent implements OnInit {

  public hotels = [];
  public isLoadHotels = false;

  constructor(private viewService: ViewService) { }

  ngOnInit(): void {
    this.searchHotels('');
  }

  //hotel searching key up event
  onKey(event: any) {
    this.searchHotels(event.target.value);
  }

  //search request
  searchHotels(searchKey: string){
    this.isLoadHotels = false;
    this.viewService
      .searchHotels(
        searchKey
      )
      .subscribe(
        (response: any) => {
          this.isLoadHotels = true;
          this.hotels = response;
        },
        (error) => {
          window.alert('Search error: ' + error);
        },
        () => {
        }
      );
  }

}
