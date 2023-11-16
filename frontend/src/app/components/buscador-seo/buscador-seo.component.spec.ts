import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuscadorSeoComponent } from './buscador-seo.component';

describe('BuscadorSeoComponent', () => {
  let component: BuscadorSeoComponent;
  let fixture: ComponentFixture<BuscadorSeoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuscadorSeoComponent]
    });
    fixture = TestBed.createComponent(BuscadorSeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
