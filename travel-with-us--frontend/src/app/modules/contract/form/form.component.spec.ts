import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormComponent } from './form.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserModule, By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';
import { ContractService } from '../service/contract.service';
import { ContractServiceMock } from '../../../mocks/contract.service.mock';
import { ContractModule } from '../contract.module';

describe('FormComponent', () => {
  let component: FormComponent;
  let fixture: ComponentFixture<FormComponent>;
  let de: DebugElement;
  let el: HTMLElement;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [FormComponent],
      imports: [
        ReactiveFormsModule,
        FormsModule,
        BrowserModule,
        ContractModule.forRoot(),
      ],
      providers: [{ provide: ContractService, useClass: ContractServiceMock }],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    de = fixture.debugElement.query(By.css('form'));
    el = de.nativeElement;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it(`should render title in a u tag`, async(() => {
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('u').textContent).toContain('Contract Form');
  }));

  it(`should set submitted to true`, async(() => {
    component.onSubmit();
    expect(component.contractForm.invalid).toBeTruthy();
  }));

  it(`Button should be disabled`, async(() => {
    let submitEL: DebugElement = fixture.debugElement.query(
      By.css('button[type=submit]')
    );
    expect(component.contractForm.valid).toBeFalsy();
  }));

  it(`form should be valid`, async(() => {
    let phoneNumbers = ["1236547895"];
    let hotel = {
      hotelId: 1,
      hotelName: "Dummy Hotel",
      address: "Dummy address",
      phone: phoneNumbers,
      email: "dummy@gmail.com",
    };

    component.contractForm.patchValue({
      name: "Dummy contract",
      description: "Contract Form",
      type: "Legal",
      hotel: hotel,
      start_date: new Date(Date.now()),
      end_date: new Date(Date.now()),
      markup: 15
    });
    component.room_types.controls[0].patchValue({
      type_name: "Dummy type",
      no_of_rooms: 1,
      price: 1,
      max_adults: 1
    });
    expect(component.contractForm.valid).toBeTruthy();
  }));
});