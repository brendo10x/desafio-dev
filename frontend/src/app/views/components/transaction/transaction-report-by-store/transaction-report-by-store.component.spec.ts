import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TransactionReportByStoreComponent } from './transaction-report-by-store.component';

describe('TransactionReportByStoreComponent', () => {
  let component: TransactionReportByStoreComponent;
  let fixture: ComponentFixture<TransactionReportByStoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TransactionReportByStoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TransactionReportByStoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
