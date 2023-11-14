import { TestBed } from '@angular/core/testing';

import { AnalizadorSeoService } from './analizador-seo.service';

describe('AnalizadorSeoService', () => {
  let service: AnalizadorSeoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AnalizadorSeoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
