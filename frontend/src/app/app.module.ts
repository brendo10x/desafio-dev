import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './views/components/template/header/header.component';
import {MatToolbarModule} from '@angular/material/toolbar';
import { FooterComponent } from './views/components/template/footer/footer.component';
import { MenuComponent } from './views/components/template/menu/menu.component';
import {MatMenuModule} from '@angular/material/menu';
import {MatIconModule} from '@angular/material/icon';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatDividerModule} from '@angular/material/divider';
import { ImportCnabComponent } from './views/components/store/import-cnab/import-cnab.component';
import { TransactionReportByStoreComponent } from './views/components/transaction/transaction-report-by-store/transaction-report-by-store.component';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MaterialFileInputModule} from "ngx-material-file-input";
import { HttpClientModule } from '@angular/common/http';
import {MatTableModule} from "@angular/material/table";
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from "@angular/material/select";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatPaginatorModule} from "@angular/material/paginator";


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    MenuComponent,
    ImportCnabComponent,
    TransactionReportByStoreComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MatToolbarModule,
        MatMenuModule,
        MatIconModule,
        MatCardModule,
        MatButtonModule,
        MatDividerModule,
        MatFormFieldModule,
        MaterialFileInputModule,
        HttpClientModule,
        MatTableModule,
        MatInputModule,
        MatSelectModule,
        MatGridListModule,
        MatPaginatorModule,

    ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
