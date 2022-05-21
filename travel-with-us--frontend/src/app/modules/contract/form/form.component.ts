import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { ContractService } from '../service/contract.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.scss'],
})
export class FormComponent implements OnInit {
  public contractForm: FormGroup;
  public hotelForm: FormGroup;
  public hotels: any[];
  public newHotel = false;
  public selectedHotel: string = 'Select Hotel';

  public minDate = new Date(Date.now());

  constructor(
    private fb: FormBuilder,
    private contractService: ContractService
  ) {}

  ngOnInit(): void {
    this.contractForm = this.fb.group({
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      type: ['', [Validators.required]],
      hotel: ['', [Validators.required]],
      start_date: ['', [Validators.required]],
      end_date: ['', [Validators.required]],
      markup: ['', [Validators.required, Validators.min(1)]],
      room_types: this.fb.array([]),
    });
    this.hotelForm = this.fb.group({
      hotel_name: ['', [Validators.required]],
      address: ['', [Validators.required]],
      phone: this.fb.array([]),
      email: ['', [Validators.required, Validators.email]],
    });
    this.getAllHotels();
    this.onAddRoomType();
    this.onAddPhoneNo();
    this.searchHotels('');
  }

  getAllHotels() {
    this.contractService.getAllHotels().subscribe((response: any) => {
      console.log('Retrieved all hotels {} ' + response);
      this.hotels = response;
    });
  }

  //adding room type
  onAddRoomType() {
    let control = <FormArray>this.contractForm.controls.room_types;
    control.push(
      this.fb.group({
        type_name: ['', [Validators.required]],
        no_of_rooms: ['', [Validators.required, Validators.min(1)]],
        price: ['', [Validators.required, Validators.min(1)]],
        max_adults: ['', [Validators.required, Validators.min(1)]],
      })
    );
  }

  //delete room type
  onDeleteRoomType(i: number) {
    this.room_types.removeAt(i);
  }

  //add phone numbers to new hotel
  onAddPhoneNo() {
    let control = <FormArray>this.hotelForm.controls.phone;
    control.push(
      this.fb.group({
        contact_no: [
          '',
          [
            Validators.required,
            Validators.minLength(10),
            Validators.maxLength(10),
            Validators.pattern('\\d+'),
          ],
        ],
      })
    );
  }

  //delete phone number from new hotel
  onDeletePhoneNo(i: number) {
    this.phone.removeAt(i);
  }

  //when adding new hotel
  onAddHotel() {
    this.hotelForm.reset();
    this.newHotel = true;
    this.hotel.disable();
  }

  onSelectHotel() {
    this.hotel.reset();
    this.newHotel = false;
    this.hotel.enable();
  }

  //called when hotel is selected
  onSelectedHotel(hotel: any) {
    this.contractForm.patchValue({
      hotel: hotel,
    });
    this.selectedHotel = hotel.hotelName;
  }

  //search key up event
  onKey(event: any) {
    this.searchHotels(event.target.value);
  }

  //search hotel by hotel name
  searchHotels(searchKey: string) {
    this.contractService.searchHotels(searchKey).subscribe(
      (response: any) => {
        this.hotels = response;
      },
      (error) => {
        window.alert('Search error: ' + error);
      },
      () => {}
    );
  }

  //submit form to the database
  onSubmit() {
    let contract = {
      name: this.name.value,
      startDate: this.start_date.value,
      endDate: this.end_date.value,
      markup: this.markup.value,
      description: this.description.value,
      type: this.type.value,
    };
    let roomTypes = [];
    this.room_types.controls.forEach((roomType) => {
      roomTypes.push({
        // @ts-ignore
        typeName: roomType.controls.type_name.value,
        // @ts-ignore
        price: roomType.controls.price.value,
        // @ts-ignore
        maxAdults: roomType.controls.max_adults.value,
        // @ts-ignore
        noOfRooms: roomType.controls.no_of_rooms.value,
      });
    });

    let hotel;
    //if new hotel
    if (this.newHotel) {
      let phoneNumbers = [];
      this.phone.controls.forEach((contactNo) => {
        phoneNumbers.push({
          // @ts-ignore
          no: contactNo.controls.contact_no.value,
        });
      });

      hotel = {
        hotelName: this.hotel_name.value,
        address: this.address.value,
        phone: phoneNumbers,
        email: this.email.value,
      };
    } else {
      //if existing hotel
      hotel = this.hotel.value;
    }

    this.contractService.addContract(contract, hotel, roomTypes).subscribe(
      (response: any) => {
        console.log('Saved contract {} : ' + response);
        this.getAllHotels();
      },
      (error) => {
        window.alert('Contract not saved: ' + error);
      },
      () => {
        window.alert('Contract successfully saved!');
      }
    );
    this.hotelForm.reset();
    this.contractForm.reset();
    this.selectedHotel = 'Select hotel';
  }

  get name() {
    return this.contractForm.get('name');
  }
  get description() {
    return this.contractForm.get('description');
  }
  get type() {
    return this.contractForm.get('type');
  }
  get hotel() {
    return this.contractForm.get('hotel');
  }
  get start_date() {
    return this.contractForm.get('start_date');
  }
  get end_date() {
    return this.contractForm.get('end_date');
  }
  get markup() {
    return this.contractForm.get('markup');
  }
  get room_types() {
    return this.contractForm.get('room_types') as FormArray;
  }
  type_name(i: number) {
    // @ts-ignore
    return this.room_types.controls[i].controls.type_name;
  }
  no_of_rooms(i: number) {
    // @ts-ignore
    return this.room_types.controls[i].controls.no_of_rooms;
  }
  price(i: number) {
    // @ts-ignore
    return this.room_types.controls[i].controls.price;
  }
  max_adults(i: number) {
    // @ts-ignore
    return this.room_types.controls[i].controls.max_adults;
  }

  get hotel_name() {
    return this.hotelForm.get('hotel_name');
  }
  get address() {
    return this.hotelForm.get('address');
  }
  get email() {
    return this.hotelForm.get('email');
  }

  get phone() {
    return this.hotelForm.get('phone') as FormArray;
  }
  contact_no(i: number) {
    // @ts-ignore
    return this.phone.controls[i].controls.contact_no;
  }
}
