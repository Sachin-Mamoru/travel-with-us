import { Injectable } from '@angular/core';
import { of } from 'rxjs';

@Injectable()
export class ContractServiceMock {
  constructor() { }

  searchHotels(searchKey: string) {
    return of([{
        "hotelId": 1,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    },
    {
        "hotelId": 2,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    }]);
  }

  getAllHotels() {
    return of([{
        "hotelId": 1,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    },
    {
        "hotelId": 2,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    }]);
  }

  addHotelContract(contract: any, hotel: any, roomTypes: any) {
    return of({
        "hotelId": 1,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    });
  }

  addContract(contract: any, hotelId: any, roomTypes: any) {
    return of({
        "hotelId": 1,
        "hotelName": "regerg",
        "address": "ergerger",
        "phone": [
            {
                "no": "452753278"
            },
            {
                "no": "3773373737"
            }
        ],
        "email": "fewfw@qrqw.wefweff"
    });
}
}
