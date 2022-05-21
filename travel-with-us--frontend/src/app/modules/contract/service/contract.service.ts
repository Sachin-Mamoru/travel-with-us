import {Injectable} from '@angular/core';
import {HttpHeaders, HttpClient} from '@angular/common/http';
import {environment} from "../../../../environments/environment.prod";

@Injectable({
  providedIn: 'root',
})
export class ContractService {
  headers = new HttpHeaders({'Content-Type': 'application/json'});
  BASE_URL = environment.base_url + '/api';

  constructor(private http: HttpClient) {
  }

  getAllHotels() {
    return this.http.get(this.BASE_URL + '/hotels', {
      headers: this.headers,
    });
  }

  //add contract with new hotel
  addContract(contract: any, hotel: any, roomTypes: any) {
    let reqBody = {
      contract: contract,
      hotel: hotel,
      roomTypes: roomTypes
    };
    return this.http
      .post(this.BASE_URL + '/contract', reqBody, {headers: this.headers});
  }

  //search hotels based on hotel name
  searchHotels(searchKey: string) {
    return this.http
      .get(this.BASE_URL + `/hotels/${searchKey}`, {headers: this.headers});
  }
}
