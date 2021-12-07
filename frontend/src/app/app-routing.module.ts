import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ImportCnabComponent} from "./views/components/store/import-cnab/import-cnab.component";
import {
  TransactionReportByStoreComponent
} from "./views/components/transaction/transaction-report-by-store/transaction-report-by-store.component";

const routes: Routes = [
  {
    path: '',
    component: ImportCnabComponent
  },
  {
    path: 'stores/import-cnab',
    component: ImportCnabComponent
  },
  {
    path: 'transactions/report-by-store',
    component: TransactionReportByStoreComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
