import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Store} from "../models/store.model";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<Store[]> {
    return this.http.get<Store[]>(environment.apiUrl + "/stores");
  }
}
