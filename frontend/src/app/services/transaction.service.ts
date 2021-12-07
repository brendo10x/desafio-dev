import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {MatPaginator} from "@angular/material/paginator";
import {environment} from "../../environments/environment";

const baseUrl = 'http://localhost:8080/api/v1';

@Injectable({
    providedIn: 'root'
})
export class TransactionService {

    constructor(private http: HttpClient) {
    }

    getAll(storeId: number, paginator: MatPaginator): Observable<any> {
        return this.http.get<any>(environment.apiUrl + "/transactions?storeId=" + storeId + "&page=" + paginator.pageIndex + "&size=" + paginator.pageSize);
    }
}
