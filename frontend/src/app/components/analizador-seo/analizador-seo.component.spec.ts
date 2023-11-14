import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnalizadorSeoComponent } from './analizador-seo.component';

describe('AnalizadorSeoComponent', () => {
  let component: AnalizadorSeoComponent;
  let fixture: ComponentFixture<AnalizadorSeoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AnalizadorSeoComponent]
    });
    fixture = TestBed.createComponent(AnalizadorSeoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
