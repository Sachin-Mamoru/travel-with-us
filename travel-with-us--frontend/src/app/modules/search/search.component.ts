import {Component, OnInit} from '@angular/core';
import {FormGroup, FormBuilder, Validators, FormArray} from '@angular/forms';
import {SearchService} from './service/search.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss'],
})
export class SearchComponent implements OnInit {
  public searchForm: FormGroup;
  public contracts: any = [];
  public initialContracts: any = [];
  public minDate = new Date(Date.now());
  public isSearching: boolean = false;

  constructor(private fb: FormBuilder, private searchService: SearchService) {
  }

  ngOnInit(): void {
    this.searchForm = this.fb.group({
      check_in_date: ['', [Validators.required]],
      no_of_nights: ['', [Validators.required ,Validators.min(1)]],
      rooms: this.fb.array([]),
    });
    this.onAddRoom();
    this.searchService.getAllContracts().subscribe(
      (response: any) => {
        console.log(response);
        this.contracts = response;
        this.initialContracts = response;
      },
      (error) => {
        window.alert('Error: ' + error);
      },
      () => {
      }
    );
  }

  //add room for search form
  onAddRoom() {
    let control = <FormArray>this.searchForm.controls.rooms;
    control.push(
      this.fb.group({
        no_of_rooms: ['',[Validators.required ,Validators.min(1)]],
        no_of_adults: ['',[Validators.required ,Validators.min(1)]],
      })
    );
  }

  //delete room from search form
  onDeleteRoom(i: number) {
    this.rooms.removeAt(i);
  }

  //search called
  onSearch() {
    let roomAdultCount = [];
    this.rooms.controls.forEach((room) => {
      roomAdultCount.push({
        // @ts-ignore
        noOfRooms: room.controls.no_of_rooms.value,
        // @ts-ignore
        noOfAdults: room.controls.no_of_adults.value,
      });
    });

    this.searchService
      .searchContracts(
        this.check_in_date.value,
        this.no_of_nights.value,
        roomAdultCount
      )
      .subscribe(
        (response: any) => {
          console.log("Search result {} : " + response);
          this.isSearching = true;
          this.contracts = response;
        },
        (error) => {
          window.alert('Search error: ' + error);
        },
        () => {
        }
      );
  }

  get check_in_date() {
    return this.searchForm.get('check_in_date');
  }

  get no_of_nights() {
    return this.searchForm.get('no_of_nights');
  }

  get rooms() {
    return this.searchForm.get('rooms') as FormArray;
  }

  no_of_rooms(i: number) {
    // @ts-ignore
    return this.rooms.controls[i].controls.no_of_rooms;
  }

  no_of_adults(i: number) {
    // @ts-ignore
    return this.rooms.controls[i].controls.no_of_adults;
  }
}
