import { TestBed } from '@angular/core/testing';

import { CxUserServiceService } from './cx-user-service.service';

describe('CxUserServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CxUserServiceService = TestBed.get(CxUserServiceService);
    expect(service).toBeTruthy();
  });
});
