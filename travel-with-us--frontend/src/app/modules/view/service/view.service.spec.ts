import { TestBed } from '@angular/core/testing';
import {HttpClientModule} from '@angular/common/http';

import { ViewService } from './view.service';

describe('ViewService', () => {
  let service: ViewService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientModule],
      providers: [ViewService]
    });
    service = TestBed.inject(ViewService);
  });

  // it('should be created', () => {
  //   expect(service).toBeTruthy();
  // });
});
