import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import {environment} from "../../../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  BASE_URL = environment.base_url + '/api';

  constructor(private http: HttpClient) {}

  //search hotel contracts based on check in date, number of nights and room adult count
  searchContracts(checkInDate: any, noOfNights: any, roomAdultCount: any) {
    let reqBody = {
      checkInDate: checkInDate,
      noOfNights: noOfNights,
      roomAdultCount: roomAdultCount
    }
    return this.http
      .post(this.BASE_URL + '/search', reqBody, {headers: this.headers});
  }

  //retrieve all contracts
  getAllContracts() {
    return this.http.get(this.BASE_URL + '/contracts', {
      headers: this.headers,
    });
  }
}
