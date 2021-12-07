import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImportCnabComponent } from './import-cnab.component';

describe('ImportCnabComponent', () => {
  let component: ImportCnabComponent;
  let fixture: ComponentFixture<ImportCnabComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImportCnabComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImportCnabComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
