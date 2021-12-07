import {Component, OnInit, ViewChild} from '@angular/core';
import {Transaction} from "../../../../models/transaction.model";
import {TransactionService} from "../../../../services/transaction.service";
import {StoreService} from "../../../../services/store.service";
import {Store} from "../../../../models/store.model";
import {MatPaginator, PageEvent} from "@angular/material/paginator";

interface Food {
    value: string;
    viewValue: string;
}

@Component({
    selector: 'app-transaction-report-by-store',
    templateUrl: './transaction-report-by-store.component.html',
    styleUrls: ['./transaction-report-by-store.component.css']
})
export class TransactionReportByStoreComponent implements OnInit {

    stores: Store[] = [];
    transactions: Transaction[] = [];
    selectedStore: Store;
    dataSource: any;
    displayedColumns: string[] = ['id', 'beneficiarysCpf', 'cardNumber', 'amount','type_description','type','transactionAt'];
    lengthElements: number;
    pageSize: number = 5;
    pageEvent: PageEvent;

    @ViewChild(MatPaginator) paginator: MatPaginator;

    constructor(private tutorialService: TransactionService,
                private storeService: StoreService) {
    }

    ngOnInit(): void {
        this.retrieveStores();
    }

    retrieveTransactions(storeId: number, paginator: MatPaginator): void {
        this.tutorialService.getAll(storeId, paginator)
            .subscribe(
                response => {
                    this.transactions = response.content;
                    this.dataSource = this.transactions;
                    this.lengthElements = response.totalElements;
                },
                error => {
                    console.log(error);
                });
    }

    retrieveStores(): void {
        this.storeService.getAll()
            .subscribe(
                response => {
                    this.stores = response;
                },
                error => {
                    console.log(error);
                });
    }

    updateTransaction(){
        this.retrieveTransactions(this.selectedStore.id, this.paginator);
    }

    onChangeSelectedStore() {
        this.paginator.firstPage();
        this.updateTransaction();
    }

    onPageChanged(e:any) {
        this.updateTransaction();
    }
}
