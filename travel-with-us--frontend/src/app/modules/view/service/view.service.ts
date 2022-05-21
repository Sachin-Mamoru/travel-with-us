import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import {environment} from "../../../../environments/environment.prod";

@Injectable({
  providedIn: 'root'
})
export class ViewService {

  headers = new HttpHeaders({ 'Content-Type': 'application/json' });
  BASE_URL = environment.base_url + '/api';

  constructor(private http: HttpClient) {}

  //searching contracts based on contract name
  searchContracts(searchKey: string) {
    return this.http
      .get(this.BASE_URL + `/contracts/${searchKey}`, {headers: this.headers});
  }

  //searching hotels based on hotel name
  searchHotels(searchKey: string) {
    return this.http
      .get(this.BASE_URL + `/hotels/${searchKey}`, {headers: this.headers});
  }
}
